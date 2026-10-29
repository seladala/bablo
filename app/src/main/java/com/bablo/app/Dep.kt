package com.bablo.app

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.net.Uri
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.security.SecureRandom

/**
 * Клиент публичного API Калькулятора Депа (DEP ID).
 * Документация: https://zenusus.serv00.net/dep/api/v1/docs.html
 */
object DepApi {
    const val BASE = "https://zenusus.serv00.net/dep"
    const val DOCS_URL = "$BASE/api/v1/docs.html"
    const val REDIRECT_URI = "bablo67://oauth"

    // Секреты CI важнее; если их нет — берём из Pi67.kt, строка 6777.
    private val clientId get() = BuildConfig.DEP_CLIENT_ID.ifBlank { PI67_CLIENT_ID }
    private val clientSecret get() = BuildConfig.DEP_CLIENT_SECRET.ifBlank { PI67_CLIENT_SECRET }

    val oauthConfigured get() = clientId.isNotBlank() && clientSecret.isNotBlank()

    data class Tokens(val access: String, val refresh: String, val expiresInSec: Long, val scope: String)

    fun newState(): String = ByteArray(16).also { SecureRandom().nextBytes(it) }.joinToString("") { "%02x".format(it) }

    fun authorizeUrl(state: String): String = Uri.parse("$BASE/oauth/authorize.php").buildUpon()
        .appendQueryParameter("client_id", clientId)
        .appendQueryParameter("redirect_uri", REDIRECT_URI)
        .appendQueryParameter("state", state)
        .build().toString()

    suspend fun exchangeCode(code: String): Tokens = token(
        JSONObject()
            .put("grant_type", "authorization_code")
            .put("code", code)
            .put("redirect_uri", REDIRECT_URI)
    )

    suspend fun refresh(refreshToken: String): Tokens = token(
        JSONObject().put("grant_type", "refresh_token").put("refresh_token", refreshToken)
    )

    /** Завершение сессии: после отзыва токен отвечает 401. Ошибки не важны — мы и так выходим. */
    suspend fun revoke(token: String) {
        runCatching { request(null, "POST", "/oauth/revoke.php", JSONObject().put("token", token)) }
    }

    private suspend fun token(body: JSONObject): Tokens {
        body.put("client_id", clientId).put("client_secret", clientSecret)
        val json = request(null, "POST", "/oauth/token.php", body)
        val access = json.optString("access_token")
        if (access.isEmpty()) throw DepException(json.optString("error").ifEmpty { "DEP не выдал токен" })
        return Tokens(access, json.optString("refresh_token"), json.optLong("expires_in", 3600), json.optString("scope"))
    }

    class DepException(message: String) : Exception(message)

    data class Profile(
        val id: Long,
        val username: String,
        val title: String,
        val calculations: Long,
        val spins: Long,
        val verified: Boolean,
        val supporter: Boolean,
    )

    data class Wallet(val depcoins: Long, val ebln: Double, val debt: Long, val nftDebt: Long)

    sealed interface Spin {
        data class Result(val result: String, val bet: Long, val win: Long, val balanceAfter: Long) : Spin
        /** Пользователь ещё не дал разрешение: открыть [confirmUrl] и опрашивать [statusUrl]. */
        data class NeedsConfirm(val confirmUrl: String, val statusUrl: String) : Spin
    }

    suspend fun profile(key: String): Profile = request(key, "GET", "/api/v1/profile.php").run {
        Profile(
            id = optLong("id"),
            username = optString("username"),
            title = optString("title"),
            calculations = optLong("calculations"),
            spins = optLong("spins"),
            verified = optBoolean("verified"),
            supporter = optBoolean("is_supporter"),
        )
    }

    suspend fun wallet(key: String): Wallet = request(key, "GET", "/api/v1/wallet.php").run {
        Wallet(optLong("depcoins"), optDouble("ebln", 0.0), optLong("debt"), optLong("nft_debt"))
    }

    suspend fun spin(key: String, bet: Long): Spin {
        val json = request(key, "POST", "/api/v1/casino.php", JSONObject().put("bet", bet))
        return if (json.optBoolean("confirmation_required")) {
            Spin.NeedsConfirm(json.optString("confirm_url"), json.optString("status_url"))
        } else {
            Spin.Result(json.optString("result"), json.optLong("bet", bet), json.optLong("win"), json.optLong("balance_after"))
        }
    }

    /** pending | approved | denied | expired */
    suspend fun spinStatus(key: String, statusUrl: String): String =
        request(key, "GET", statusUrl).optString("status", "expired")

    private suspend fun request(key: String?, method: String, path: String, body: JSONObject? = null): JSONObject =
        withContext(Dispatchers.IO) {
            val conn = URL(if (path.startsWith("http")) path else BASE + path).openConnection() as HttpURLConnection
            try {
                conn.requestMethod = method
                conn.connectTimeout = 10_000
                conn.readTimeout = 15_000
                if (key != null) conn.setRequestProperty("Authorization", "Bearer ${key.trim()}")
                conn.setRequestProperty("Accept", "application/json")
                if (body != null) {
                    conn.doOutput = true
                    conn.setRequestProperty("Content-Type", "application/json")
                    conn.outputStream.use { it.write(body.toString().toByteArray()) }
                }
                val code = conn.responseCode
                val text = (if (code in 200..299) conn.inputStream else conn.errorStream)
                    ?.bufferedReader()?.use { it.readText() }.orEmpty()
                val json = runCatching { JSONObject(text) }.getOrNull()
                // Спин без разрешения может прийти не-200, но с confirmation_required — это не ошибка.
                if (json?.optBoolean("confirmation_required") == true) return@withContext json
                if (code !in 200..299 || json == null) {
                    throw DepException(
                        when (code) {
                            401 -> "Ключ не подходит или отозван (401)"
                            403 -> json?.optString("error")?.takeIf { it.isNotEmpty() } ?: "Нет нужного скоупа (403)"
                            429 -> "Слишком часто, подожди минутку (429)"
                            else -> json?.optString("error")?.takeIf { it.isNotEmpty() } ?: "DEP ответил $code"
                        }
                    )
                }
                json
            } finally {
                conn.disconnect()
            }
        }
}
