package com.bablo.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

/** Обновление прямо из приложения: смотрим последний релиз на GitHub и ставим его APK. */
object Updater {
    private const val LATEST = "https://api.github.com/repos/seladala/bablo/releases/latest"
    const val RELEASES_PAGE = "https://github.com/seladala/bablo/releases"

    data class Release(val build: Int, val title: String, val notes: String, val apkUrl: String, val size: Long)

    val currentBuild get() = BuildConfig.BUILD_NUMBER

    /** Последний релиз bablo-N с APK или null, если релизов нет. */
    suspend fun latest(): Release? = withContext(Dispatchers.IO) {
        val conn = URL(LATEST).openConnection() as HttpURLConnection
        try {
            conn.connectTimeout = 10_000
            conn.readTimeout = 15_000
            conn.setRequestProperty("Accept", "application/vnd.github+json")
            if (conn.responseCode == 404) return@withContext null
            if (conn.responseCode != 200) throw Exception("GitHub ответил ${conn.responseCode}")
            val json = JSONObject(conn.inputStream.bufferedReader().use { it.readText() })
            val build = json.optString("tag_name").removePrefix("bablo-").toIntOrNull() ?: return@withContext null
            val assets = json.optJSONArray("assets") ?: return@withContext null
            val apk = (0 until assets.length()).map { assets.getJSONObject(it) }
                .firstOrNull { it.optString("name").endsWith(".apk") } ?: return@withContext null
            Release(
                build = build,
                title = json.optString("name").ifEmpty { "Сборка $build" },
                notes = json.optString("body"),
                apkUrl = apk.optString("browser_download_url"),
                size = apk.optLong("size"),
            )
        } finally {
            conn.disconnect()
        }
    }

    /** Качает APK в кэш; [onProgress] получает 0..1. */
    suspend fun download(context: Context, release: Release, onProgress: (Float) -> Unit): File = withContext(Dispatchers.IO) {
        val dir = File(context.cacheDir, "updates").apply { mkdirs() }
        dir.listFiles()?.forEach { it.delete() }
        val file = File(dir, "bablo-${release.build}.apk")
        val conn = URL(release.apkUrl).openConnection() as HttpURLConnection
        try {
            conn.connectTimeout = 15_000
            conn.readTimeout = 30_000
            conn.instanceFollowRedirects = true
            if (conn.responseCode != 200) throw Exception("Скачивание: ответ ${conn.responseCode}")
            val total = conn.contentLengthLong.takeIf { it > 0 } ?: release.size
            conn.inputStream.use { input ->
                file.outputStream().use { out ->
                    val buf = ByteArray(64 * 1024)
                    var done = 0L
                    while (true) {
                        val n = input.read(buf)
                        if (n < 0) break
                        out.write(buf, 0, n)
                        done += n
                        if (total > 0) onProgress((done.toFloat() / total).coerceIn(0f, 1f))
                    }
                }
            }
            file
        } finally {
            conn.disconnect()
        }
    }

    /** Android 8+ требует разрешить установку из этого приложения. */
    fun canInstall(context: Context) =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.O || context.packageManager.canRequestPackageInstalls()

    fun openInstallPermission(context: Context) {
        context.startActivity(
            Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:${context.packageName}"))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    fun install(context: Context, apk: File) {
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.updates", apk)
        context.startActivity(
            Intent(Intent.ACTION_VIEW)
                .setDataAndType(uri, "application/vnd.android.package-archive")
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}

/** Диалог «есть новая версия»: скачать, дать разрешение, установить. */
@Composable
fun UpdateDialog(release: Updater.Release, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var progress by remember { mutableFloatStateOf(-1f) }
    var apk by remember { mutableStateOf<File?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    fun installOrAsk(file: File) {
        if (Updater.canInstall(context)) Updater.install(context, file) else Updater.openInstallPermission(context)
    }

    AlertDialog(
        onDismissRequest = { if (progress < 0f || apk != null) onDismiss() },
        icon = { Text("⬇️", fontSize = 36.sp) },
        title = { Text("Новая версия БАБЛО 67!") },
        text = {
            Column {
                Text("${release.title}\nУ тебя сборка ${Updater.currentBuild}, на GitHub — ${release.build}.")
                if (release.size > 0) Text("Размер: ${"%.1f".format(release.size / 1_048_576.0)} МБ", fontSize = 12.sp, color = Color.White.copy(alpha = 0.6f))
                if (progress >= 0f) {
                    Spacer(Modifier.height(12.dp))
                    LinearProgressIndicator(progress = { progress }, modifier = Modifier.fillMaxWidth())
                    Text(if (apk != null) "Скачано ✅" else "Качаем… ${(progress * 100).toInt()}%", fontSize = 12.sp)
                }
                if (apk != null && !Updater.canInstall(context)) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Разреши БАБЛО устанавливать приложения, вернись и нажми «Установить» ещё раз.",
                        fontSize = 12.sp,
                        color = Color(0xFFFFCC80),
                    )
                }
                error?.let {
                    Spacer(Modifier.height(8.dp))
                    Text(it, color = Color(0xFFFF8A80), fontSize = 12.sp)
                }
            }
        },
        confirmButton = {
            val file = apk
            when {
                file != null -> Button(onClick = { installOrAsk(file) }) { Text("Установить ✅", fontWeight = FontWeight.Bold) }
                progress >= 0f -> Button(onClick = {}, enabled = false) { Text("Качаем…") }
                else -> Button(onClick = {
                    error = null
                    progress = 0f
                    scope.launch {
                        try {
                            val f = Updater.download(context, release) { p -> progress = p }
                            apk = f
                            progress = 1f
                            installOrAsk(f)
                        } catch (e: CancellationException) {
                            throw e
                        } catch (e: Exception) {
                            progress = -1f
                            error = e.message ?: "Не скачалось"
                        }
                    }
                }) { Text("Скачать и обновить ⬇️", fontWeight = FontWeight.Bold) }
            }
        },
        dismissButton = { TextButton(onClick = onDismiss, enabled = progress < 0f || apk != null) { Text("Потом") } },
    )
}

/** Кнопка «Проверить обновления» — сама проверяет и сама показывает диалог. */
@Composable
fun UpdateCheckButton() {
    val scope = rememberCoroutineScope()
    var found by remember { mutableStateOf<Updater.Release?>(null) }
    var status by remember { mutableStateOf<String?>(null) }
    var busy by remember { mutableStateOf(false) }
    Column {
        androidx.compose.material3.OutlinedButton(
            onClick = {
                busy = true
                status = null
                scope.launch {
                    try {
                        val r = Updater.latest()
                        if (r != null && r.build > Updater.currentBuild) found = r
                        else status = "У тебя последняя версия ✅ (сборка ${Updater.currentBuild})"
                    } catch (e: CancellationException) {
                        throw e
                    } catch (e: Exception) {
                        status = "Не удалось проверить: ${e.message}"
                    } finally {
                        busy = false
                    }
                }
            },
            enabled = !busy,
            modifier = Modifier.fillMaxWidth(),
        ) { Text(if (busy) "Проверяем…" else "🔄 Проверить обновления") }
        status?.let { Text(it, fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f)) }
    }
    found?.let { UpdateDialog(it) { found = null } }
}
