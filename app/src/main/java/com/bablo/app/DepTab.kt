package com.bablo.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val BETS = listOf(67L, 670L, 1_000L, 6_767L)

private fun depResultText(r: DepApi.Spin.Result) = when (r.result) {
    "jackpot" -> "🤑 ДЖЕКПОТ! +${r.win} ДК"
    "win" -> "✅ Выигрыш +${r.win} ДК"
    "small_win" -> "🙂 Мелкий плюс +${r.win} ДК"
    else -> "💀 Минус депчик (−${r.bet} ДК)"
}

/** Вкладка интеграции с Калькулятором Депа: вход по API-ключу, профиль, кошелёк, слоты. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepTab(game: BabloGame, onDep: (DepApi.Spin.Result) -> Unit, onDenied: () -> Unit) {
    val scope = rememberCoroutineScope()
    val uri = LocalUriHandler.current
    var keyInput by remember { mutableStateOf(game.depKey) }
    var profile by remember { mutableStateOf<DepApi.Profile?>(null) }
    var wallet by remember { mutableStateOf<DepApi.Wallet?>(null) }
    var status by remember { mutableStateOf<String?>(null) }
    var busy by remember { mutableStateOf(false) }
    var bet by remember { mutableLongStateOf(BETS.first()) }
    var lastSpin by remember { mutableStateOf<DepApi.Spin.Result?>(null) }

    suspend fun refresh() {
        // Профиль и кошелёк в разных скоупах: показываем то, что разрешено ключом.
        runCatching { DepApi.profile(game.depKey) }
            .onSuccess { profile = it }
            .onFailure { status = "Профиль: ${it.message}" }
        runCatching { DepApi.wallet(game.depKey) }
            .onSuccess { wallet = it }
            .onFailure { status = "Кошелёк: ${it.message}" }
    }

    LaunchedEffect(game.depKey) {
        if (game.depKey.isNotBlank()) {
            busy = true
            status = null
            refresh()
            busy = false
        }
    }

    fun spin() = scope.launch {
        busy = true
        status = "🎰 Крутим…"
        try {
            var res = DepApi.spin(game.depKey, bet)
            if (res is DepApi.Spin.NeedsConfirm) {
                status = "Подтверди спин в браузере DEP ID и возвращайся 🤝"
                uri.openUri(res.confirmUrl)
                // Запрос живёт 15 минут; опрашиваем бережно, чтобы не словить 429.
                var decision = "pending"
                val until = System.currentTimeMillis() + 15 * 60_000L
                while (decision == "pending" && System.currentTimeMillis() < until) {
                    delay(3_000)
                    decision = DepApi.spinStatus(game.depKey, res.statusUrl)
                }
                if (decision != "approved") {
                    status = if (decision == "denied") "Отказано. Деп не состоялся 🚫" else "Время на подтверждение вышло ⌛"
                    onDenied()
                    return@launch
                }
                res = DepApi.spin(game.depKey, bet)
            }
            if (res is DepApi.Spin.Result) {
                lastSpin = res
                status = null
                onDep(res)
                refresh()
            } else {
                status = "DEP снова просит подтверждение — попробуй ещё раз"
            }
        } catch (e: Exception) {
            status = e.message ?: "Ошибка сети"
            onDenied()
        } finally {
            busy = false
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("🎰 Калькулятор Депа", fontWeight = FontWeight.Black, fontSize = 20.sp)
        Text(
            "Подключи DEP ID: крути слоты прямо с кнопки БАБЛО. Каждый деп даёт бабло в игре, джекпот — БАБЛО-БЕЗУМИЕ.",
            fontSize = 13.sp,
            color = Color.White.copy(alpha = 0.7f),
        )

        if (game.depKey.isBlank()) {
            OutlinedTextField(
                value = keyInput,
                onValueChange = { keyInput = it.trim() },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("API-ключ DEP") },
                visualTransformation = PasswordVisualTransformation(),
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { game.depKey = keyInput; game.save() }, enabled = keyInput.isNotBlank()) {
                    Text("Подключить ✅")
                }
                TextButton(onClick = { uri.openUri(DepApi.DOCS_URL) }) { Text("Где взять ключ?") }
            }
            Text(
                "Нужные скоупы: profile, wallet, casino. Ключ хранится только на телефоне.",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.6f),
            )
        } else {
            ElevatedCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    val p = profile
                    if (p != null) {
                        Text(
                            "${p.title} ${p.username}".trim() + (if (p.verified) " ✔️" else "") + (if (p.supporter) " 💎" else ""),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                        Text("🧮 Расчётов: ${p.calculations} · 🎰 Спинов: ${p.spins}", fontSize = 13.sp)
                    } else {
                        Text("DEP ID", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    wallet?.let { w ->
                        Text("🪙 ДепКоины: ${w.depcoins}", fontWeight = FontWeight.Bold)
                        Text("💶 €bln: ${"%.2f".format(w.ebln)}", fontSize = 13.sp)
                        if (w.debt > 0 || w.nftDebt > 0) {
                            Text("📉 Долг: ${w.debt} (NFT: ${w.nftDebt})", fontSize = 13.sp, color = Color(0xFFFF8A80))
                        }
                    }
                }
            }

            Text("Ставка, ДК", fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                BETS.forEach { b ->
                    FilterChip(selected = bet == b, onClick = { bet = b }, label = { Text(b.toString()) })
                }
            }
            Button(
                onClick = { spin() },
                enabled = !busy,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(18.dp),
            ) {
                if (busy) {
                    CircularProgressIndicator(Modifier.width(22.dp).height(22.dp), strokeWidth = 2.dp)
                    Spacer(Modifier.width(10.dp))
                }
                Text("🎰 ДЕПНУТЬ $bet", fontWeight = FontWeight.Black, fontSize = 18.sp)
            }
            lastSpin?.let { r ->
                Text(depResultText(r), fontWeight = FontWeight.Bold, color = if (r.win > 0) Color(0xFFFFD600) else Color(0xFFFF8A80))
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { scope.launch { busy = true; status = null; refresh(); busy = false } }, enabled = !busy) {
                    Text("🔄 Обновить")
                }
                TextButton(onClick = {
                    game.depKey = ""
                    game.save()
                    keyInput = ""
                    profile = null
                    wallet = null
                    lastSpin = null
                    status = null
                }) { Text("Выйти") }
            }
            Text("🎰 Депов из игры: ${game.depSpins} · 🤑 джекпотов: ${game.depJackpots}", fontSize = 12.sp, color = Color.White.copy(alpha = 0.6f))
        }

        status?.let { Text(it, color = Color(0xFFFFCC80), fontSize = 13.sp) }
    }
}
