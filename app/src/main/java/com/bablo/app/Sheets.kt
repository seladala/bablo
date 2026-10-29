package com.bablo.app

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

private val TABS = listOf("🏭 Бизнес", "👆 Кнопка", "🧖 Хаммам", "🎨 Скины", "🏆 Ачивки", "📊 Стата", "🎰 ДЕП")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BabloSheet(
    game: BabloGame,
    initialTab: Int,
    onDismiss: () -> Unit,
    onBuy: () -> Unit,
    onHammamUp: () -> Unit,
    onDenied: () -> Unit,
    onDep: (DepApi.Spin.Result) -> Unit,
) {
    var tab by remember { mutableIntStateOf(initialTab) }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    ) {
        Column(Modifier.padding(horizontal = 16.dp)) {
            Text("💰 " + formatBablo(game.money), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Black, fontSize = 24.sp)
        }
        ScrollableTabRow(selectedTabIndex = tab, edgePadding = 8.dp) {
            TABS.forEachIndexed { i, title ->
                Tab(selected = tab == i, onClick = { tab = i }, text = { Text(title) })
            }
        }
        LazyColumn(
            Modifier.heightIn(min = 300.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            when (tab) {
                0 -> businessTab(game, onBuy)
                1 -> perksTab(game, onBuy)
                2 -> hammamTab(game, onHammamUp, onDenied)
                3 -> items(Skin.entries) { SkinRow(game, it, onBuy) }
                4 -> {
                    item {
                        Text(
                            "Открыто ${game.unlocked.size} из ${Achievement.entries.size}",
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    items(Achievement.entries) { AchievementRow(it, it in game.unlocked) }
                }
                5 -> item { StatsTab(game) }
                else -> item { DepTab(game, onDep, onDenied) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun LazyListScope.businessTab(game: BabloGame, onBuy: () -> Unit) {
    item {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Покупать по:", Modifier.weight(1f), color = Color.White.copy(alpha = 0.7f))
            listOf(1 to "x1", 10 to "x10", 0 to "МАКС").forEach { (n, label) ->
                FilterChip(
                    selected = game.buyAmount == n,
                    onClick = { game.buyAmount = n },
                    label = { Text(label) },
                    modifier = Modifier.padding(start = 6.dp),
                )
            }
        }
    }
    val visible = Gen.entries.filter { game.genVisible(it) }
    items(visible) { GenRow(game, it, onBuy) }
    Gen.entries.firstOrNull { !game.genVisible(it) }?.let { next ->
        item {
            Card(
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.3f)),
            ) {
                Text(
                    "❓ ???  —  купи «${Gen.entries[next.ordinal - 1].title}», чтобы открыть",
                    Modifier.padding(16.dp),
                    color = Color.White.copy(alpha = 0.5f),
                )
            }
        }
    }
}

@Composable
private fun GenRow(game: BabloGame, g: Gen, onBuy: () -> Unit) {
    val n = game.count(g)
    val next = game.nextMilestone(g)
    ElevatedCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(g.emoji, fontSize = 32.sp, modifier = Modifier.graphicsLayer { alpha = if (n > 0) 1f else 0.4f })
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text("${g.title}  ×$n", fontWeight = FontWeight.Bold)
                Text(
                    if (n > 0) "+${formatBablo(game.genEach(g) * n)}/сек · каждый ${formatBablo(game.genEach(g))}"
                    else g.flavor + " · ${formatBablo(game.genEach(g))}/сек",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.7f),
                )
                if (next != null && n > 0) {
                    Text(
                        "🎯 ещё ${next - n} до x2 (у тебя x${formatBablo(game.genMilestoneMult(g))})",
                        fontSize = 11.sp,
                        color = Color(0xFFFFD600).copy(alpha = 0.8f),
                    )
                }
            }
            Spacer(Modifier.width(6.dp))
            Button(
                onClick = { if (game.buyGen(g)) onBuy() },
                enabled = game.canBuyGen(g),
                contentPadding = PaddingValues(horizontal = 10.dp),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(formatBablo(game.genCost(g)), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    if (game.buyAmount != 1) Text("x${game.buyCount(g)}", fontSize = 10.sp)
                }
            }
        }
    }
}

private fun LazyListScope.perksTab(game: BabloGame, onBuy: () -> Unit) {
    val available = Perk.entries.filter { game.perkAvailable(it) }.sortedBy { it.cost }
    val locked = Perk.entries.filter { !game.has(it) && !game.perkAvailable(it) }
    val owned = Perk.entries.filter { game.has(it) }
    item {
        Text(
            "Улучшено ${owned.size} из ${Perk.entries.size}. Каждое меняет, как работает кнопка.",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 13.sp,
        )
    }
    items(available) { PerkRow(game, it, onBuy) }
    items(locked) { p ->
        Text(
            "🔒 ${p.emoji} ${p.title} — сначала «${p.requiresPerk?.title}»",
            color = Color.White.copy(alpha = 0.4f),
            fontSize = 13.sp,
        )
    }
    if (owned.isNotEmpty()) {
        item { Text("Куплено", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 6.dp)) }
        items(owned) { p ->
            Text("✅ ${p.emoji} ${p.title} — ${p.description}", color = Color(0xFFB9F6CA), fontSize = 13.sp)
        }
    }
}

@Composable
private fun PerkRow(game: BabloGame, p: Perk, onBuy: () -> Unit) {
    ElevatedCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(p.emoji, fontSize = 30.sp)
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(p.title, fontWeight = FontWeight.Bold)
                Text(p.description, style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.7f))
            }
            Spacer(Modifier.width(6.dp))
            Button(
                onClick = { if (game.buyPerk(p)) onBuy() },
                enabled = game.canBuyPerk(p),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                Text(formatBablo(p.cost), fontWeight = FontWeight.Bold)
            }
        }
    }
}

private fun LazyListScope.hammamTab(game: BabloGame, onUp: () -> Unit, onDenied: () -> Unit) {
    item { HammamBuyCard(game, onDenied) }
    item { HammamUpgradeCard(game, onUp) }
    item {
        Text("Этапы хаммама", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
    }
    items(HAMMAM_STAGES.size) { i ->
        val done = i < game.hammamLevel
        val st = HAMMAM_STAGES[i]
        Column {
            Text(
                (if (done) "✅ " else if (i == game.hammamLevel) "👉 " else "⬜ ") + "${i + 1}. ${st.title}",
                color = if (done) Color(0xFFFFCC80) else Color.White.copy(alpha = if (i == game.hammamLevel) 0.9f else 0.5f),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            )
            Text(
                st.perk + " · x1.5 бабла",
                color = Color.White.copy(alpha = if (done) 0.75f else 0.4f),
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 26.dp),
            )
        }
    }
}

@Composable
private fun HammamBuyCard(game: BabloGame, onDenied: () -> Unit) {
    val shake = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    var message by remember { mutableStateOf<String?>(null) }

    Card(
        Modifier
            .fillMaxWidth()
            .graphicsLayer { translationX = shake.value },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3B2A1A)),
        border = BorderStroke(2.dp, Color(0xFFFFB74D)),
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🧖", fontSize = 36.sp)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Хаммам  ·  легендарный", fontWeight = FontWeight.Bold, color = Color(0xFFFFCC80))
                    Text(
                        "+∞ к расслабону. Цена: ∞",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.7f),
                    )
                }
                Spacer(Modifier.width(8.dp))
                OutlinedButton(
                    onClick = {
                        message = game.tryBuyHammam()
                        onDenied()
                        scope.launch {
                            for (x in listOf(24f, -24f, 18f, -18f, 10f, -10f, 0f)) shake.animateTo(x, tween(40))
                        }
                    },
                    border = BorderStroke(1.dp, Color(0xFFFF5252)),
                    contentPadding = PaddingValues(horizontal = 12.dp),
                ) {
                    Text("🔒 КУПИТЬ", color = Color(0xFFFF5252), fontWeight = FontWeight.Bold)
                }
            }
            message?.let {
                Spacer(Modifier.height(8.dp))
                Surface(shape = RoundedCornerShape(12.dp), color = Color.Black.copy(alpha = 0.35f)) {
                    Text(
                        "$it  (попыток: ${game.hammamAttempts})",
                        Modifier.padding(10.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFFFCC80),
                    )
                }
            }
        }
    }
}

@Composable
private fun HammamUpgradeCard(game: BabloGame, onUp: () -> Unit) {
    val lvl = game.hammamLevel
    Card(
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A1F14)),
    ) {
        Column(Modifier.padding(14.dp)) {
            Text(
                "♨️ Улучшение хаммама  ·  ур. $lvl / ${HAMMAM_STAGES.size}",
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFCC80),
            )
            Text(
                if (lvl == 0) "Хаммам не куплен. Но улучшать его никто не запрещал." else "Текущий: ${HAMMAM_STAGES[lvl - 1].title}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.75f),
            )
            Text(
                "Сейчас: x${formatBablo(Math.pow(1.5, lvl.toDouble()))} бабла" +
                    if (lvl > 0) ", жар ${game.temperature.toInt()}° из ${game.maxTemperature.toInt()}°" else "",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.75f),
            )
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { lvl / HAMMAM_STAGES.size.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = Color(0xFFFFB74D),
            )
            if (!game.hammamMaxed) {
                Text(
                    "Следующий этап: " + HAMMAM_STAGES[lvl].perk,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFFFCC80),
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { if (game.upgradeHammam()) onUp() },
                enabled = game.canUpgradeHammam(),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    if (game.hammamMaxed) "ХАММАМ АБСОЛЮТЕН" else "«${HAMMAM_STAGES[lvl].title}» · ${formatBablo(game.hammamCost)}",
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
private fun SkinRow(game: BabloGame, s: Skin, onBuy: () -> Unit) {
    val owned = s in game.ownedSkins
    val selected = game.skin == s
    val lock = game.skinLockReason(s)
    ElevatedCard(
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(s.emoji, fontSize = 30.sp)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(s.title, fontWeight = FontWeight.Bold)
                Text(
                    "Бонус x${s.bonus}" + (lock?.let { " · 🔒 $it" } ?: ""),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.7f),
                )
            }
            when {
                selected -> TextButton(onClick = {}, enabled = false) { Text("✅ Надет") }
                owned -> OutlinedButton(onClick = { game.skin = s }) { Text("Надеть") }
                else -> Button(
                    onClick = { if (game.buySkin(s)) onBuy() },
                    enabled = lock == null && game.money >= s.price,
                    contentPadding = PaddingValues(horizontal = 14.dp),
                ) {
                    Text(formatBablo(s.price), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun AchievementRow(a: Achievement, unlocked: Boolean) {
    Card(
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (unlocked) Color(0xFF33300A) else Color.Black.copy(alpha = 0.25f),
        ),
        border = if (unlocked) BorderStroke(1.dp, Color(0xFFFFD600)) else null,
    ) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(if (unlocked) a.emoji else "❓", fontSize = 26.sp)
            Spacer(Modifier.width(12.dp))
            Column {
                Text(a.title, fontWeight = FontWeight.Bold, color = if (unlocked) Color(0xFFFFD600) else Color.White.copy(alpha = 0.6f))
                Text(a.description, fontSize = 12.sp, color = Color.White.copy(alpha = 0.6f))
            }
        }
    }
}

@Composable
private fun StatsTab(game: BabloGame) {
    val rows = listOf(
        "💰 Всего заработано" to formatBablo(game.totalEarned),
        "👆 Тыков" to game.totalClicks.toString(),
        "🔥 Макс. комбо" to "x${game.maxCombo}",
        "✨ Золотых мешков" to game.goldenCaught.toString(),
        "🧖 Попыток купить хаммам" to game.hammamAttempts.toString(),
        "♨️ Уровень хаммама" to "${game.hammamLevel} / ${HAMMAM_STAGES.size}",
        "💳 Выводов" to game.withdrawals.toString(),
        "🎟️ Жетонов 67" to "${game.tokens} (+${(game.tokens * 67)}%)",
        "🏭 Генераторов" to game.totalGens().toString(),
        "👆 Улучшений кнопки" to "${game.ownedPerks.size} / ${Perk.entries.size}",
        "🏆 Ачивки" to "${game.unlocked.size} (+${game.unlocked.size}% бабла)",
        "♨️ Жар хаммама" to "${game.temperature.toInt()}° / ${game.maxTemperature.toInt()}°",
        "✖️ Общий множитель" to "x" + formatBablo(game.multiplier),
        "⚡ Шанс крита" to "${(game.critChance * 100).toInt()}%",
        "✊ Скорость зажатия" to "${(game.holdRate * 0.5).toInt()}–${(game.holdRate * 1.5).toInt()} тык/сек",
        "😴 Оффлайн-доход" to "${(game.offlineRate * 100).toInt()}%",
        "🫧 Пузырей поймано" to "${game.bubblesPopped} (лучшая серия ${game.bestBubbleStreak})",
        "👑 Ультра / 🌈 Ультра супер" to "${game.ultraUses} / ${game.ultraSuperUses}",
        "📞 Звонков принято" to game.callsAnswered.toString(),
        "🎰 Депов / джекпотов" to "${game.depSpins} / ${game.depJackpots}",
    )
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        rows.forEach { (k, v) ->
            Row(Modifier.fillMaxWidth()) {
                Text(k, Modifier.weight(1f), color = Color.White.copy(alpha = 0.75f))
                Text(v, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun WithdrawDialog(game: BabloGame, onDismiss: () -> Unit, onWithdraw: () -> Unit, onDenied: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = { Text("💳", fontSize = 36.sp) },
        title = { Text("Вывод бабла") },
        text = {
            Column {
                Text("Минимальная сумма вывода — 68 квинтиллиардов бабла (68 × 10³³).")
                Spacer(Modifier.height(8.dp))
                Text("У тебя: ${formatBablo(game.money)} · прогресс по нулям ${(game.withdrawProgress * 100).toInt()}%")
                Spacer(Modifier.height(8.dp))
                if (game.canWithdraw) {
                    Text(
                        "Вывод обнулит бабло и улучшения кнопки, но даст ${game.tokensForWithdraw} жетон(ов) 67: " +
                            "+67% ко всему баблу навсегда за каждый. Хаммам, скины и ачивки останутся.",
                        fontWeight = FontWeight.Bold,
                    )
                } else {
                    Text("Банк говорит: «Приходи, когда будет 68 квинтиллиардов» 🏦", color = Color(0xFFFF8A80))
                }
            }
        },
        confirmButton = {
            Button(onClick = { if (game.canWithdraw) onWithdraw() else onDenied() }, enabled = true) {
                Text(if (game.canWithdraw) "ВЫВЕСТИ 💸" else "🔒 Недоступно")
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Закрыть") } },
    )
}
