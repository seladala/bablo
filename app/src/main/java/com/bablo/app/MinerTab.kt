package com.bablo.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private fun hz(v: Double) = when {
    v >= 1e6 -> "%.2f МГц".format(v / 1e6)
    v >= 1e3 -> "%.1f кГц".format(v / 1e3)
    else -> "%.0f Гц".format(v)
}

/** Вкладка майнера: процессор БУРМАЛДА-67 майнит бабло, каждый блок — новый знак π. */
@Composable
fun MinerTab(game: BabloGame, onUpgrade: () -> Unit, onPi: (BabloGame.BlockReward) -> Unit, onDenied: () -> Unit) {
    @Suppress("UNUSED_VARIABLE") val tick = game.clock // перерисовка 10 раз в секунду
    val cpu = game.cpu
    val mono = FontFamily.Monospace

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("⛏️ Майнер бабла БУРМАЛДА-67", fontWeight = FontWeight.Black, fontSize = 20.sp)
        Text(
            "Настоящий эмулятор процессора гоняет программу на ассемблере: перебирает nonce, " +
                "хэш делится на 67 — блок! За каждый блок — бабло и новый знак π.",
            fontSize = 13.sp,
            color = Color.White.copy(alpha = 0.7f),
        )

        ElevatedCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    if (game.cpuLevel == 0) "Частота: процессор выключен" else "Частота: ${hz(game.cpuHz)} · уровень ${game.cpuLevel}/${BabloGame.CPU_MAX_LEVEL}",
                    fontWeight = FontWeight.Bold,
                )
                if (cpu == null) {
                    Text("💾 Ассемблер собирает программу…", fontSize = 13.sp)
                } else {
                    Text(
                        "Программа: ${cpu.program.sourceLines} строк, ${cpu.program.size} инструкций",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.6f),
                    )
                    Text("PC=%05d  SP=%d  > %s".format(cpu.pc, cpu.sp, cpu.currentText), fontFamily = mono, fontSize = 11.sp, maxLines = 1)
                    for (row in 0 until 4) {
                        Text(
                            (0 until 2).joinToString("  ") { col ->
                                val i = row * 2 + col
                                "R$i=%016X".format(cpu.r[i])
                            },
                            fontFamily = mono,
                            fontSize = 11.sp,
                        )
                    }
                    Text("nonce ${cpu.mem[BURMALDA_NONCE_ADDR]}", fontFamily = mono, fontSize = 11.sp)
                }
                Text("🧱 Блоков: ${game.blocksFound} · 🧠 инструкций: ${game.cpuCycles}", fontSize = 13.sp)
                if (game.cpuReboots > 0) Text("♻️ Перезагрузок: ${game.cpuReboots}", fontSize = 12.sp, color = Color(0xFFFFCC80))
            }
        }

        Button(
            onClick = { if (game.upgradeCpu()) onUpgrade() else onDenied() },
            enabled = !game.cpuMaxed,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
        ) {
            Text(
                when {
                    game.cpuMaxed -> "🔥 Максимальный разгон"
                    game.cpuLevel == 0 -> "⛏️ Запустить майнер · ${formatBablo(game.cpuCost)}"
                    else -> "⬆️ Разогнать до ${hz(game.cpuHz * 2)} · ${formatBablo(game.cpuCost)}"
                },
                fontWeight = FontWeight.Black,
            )
        }

        Text("🥧 Число π", fontWeight = FontWeight.Black, fontSize = 18.sp)
        val digits = game.piDigits
        val shownPi = when {
            digits.isEmpty() -> "π = ?"
            game.piCount <= 200 -> "π = " + digits.first() + (if (digits.length > 1) "." + digits.drop(1) else "")
            else -> "π = …" + digits.takeLast(200)
        }
        Text(shownPi, fontFamily = mono, fontSize = 12.sp, lineHeight = 15.sp)
        Text(
            "Знаков: ${game.piCount} · «67» найдено: ${game.piSixSevens}",
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.6f),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilledTonalButton(onClick = { onPi(game.computePiDigit()) }) {
                Text("🔢 Вычислить следующий знак π")
            }
        }
    }
}
