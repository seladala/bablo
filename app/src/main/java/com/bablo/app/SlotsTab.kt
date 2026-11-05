package com.bablo.app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val BET_FRACTIONS = listOf(0.1 to "10%", 0.25 to "25%", 0.5 to "50%")

/** Слоты бабла: крутятся на игровое бабло, без всяких API. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlotsTab(game: BabloGame, onSpin: () -> Unit, onResult: (BabloGame.SlotResult) -> Unit, onDenied: () -> Unit) {
    var fraction by remember { mutableDoubleStateOf(0.1) }
    var reels by remember { mutableStateOf(listOf("6️⃣", "7️⃣", "💸")) }
    var pending by remember { mutableStateOf<BabloGame.SlotResult?>(null) }
    var shown by remember { mutableStateOf<BabloGame.SlotResult?>(null) }

    // Анимация барабанов: крутим случайные символы, потом останавливаем по одному.
    LaunchedEffect(pending) {
        val res = pending ?: return@LaunchedEffect
        val stopped = mutableListOf<String>()
        for (reel in 0 until 3) {
            repeat(7 + reel * 4) {
                reels = stopped + List(3 - stopped.size) { BabloGame.SLOT_SYMBOLS.random() }
                delay(60)
            }
            stopped += res.reels[reel]
            reels = stopped + List(3 - stopped.size) { BabloGame.SLOT_SYMBOLS.random() }
        }
        shown = res
        pending = null
        onResult(res)
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("🎲 Слоты бабла", fontWeight = FontWeight.Black, fontSize = 20.sp)
        Text(
            "👑👑👑 — x67 · три одинаковых — x15 · 6️⃣7️⃣ подряд — x6.7 · пара — вернём " +
                (if (game.has(Perk.SLOT_INSURANCE)) "80%" else "67%"),
            fontSize = 13.sp,
            color = Color.White.copy(alpha = 0.7f),
        )
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
            reels.forEach { sym ->
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = Color.Black.copy(alpha = 0.4f),
                    border = BorderStroke(2.dp, Color(0xFFFFD600)),
                ) {
                    Box(Modifier.size(84.dp), contentAlignment = Alignment.Center) {
                        Text(sym, fontSize = 44.sp)
                    }
                }
            }
        }
        shown?.let { r ->
            Text(
                r.title + if (r.win > 0) "  +${formatBablo(r.win)}" else "  −${formatBablo(r.bet)}",
                fontWeight = FontWeight.Bold,
                color = if (r.win >= r.bet) Color(0xFFFFD600) else Color(0xFFFF8A80),
            )
        }
        Text("Ставка от бабла", fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            BET_FRACTIONS.forEach { (f, label) ->
                FilterChip(selected = fraction == f, onClick = { fraction = f }, label = { Text(label) })
            }
        }
        Button(
            onClick = {
                val res = game.spinSlots(fraction)
                if (res == null) onDenied() else {
                    shown = null
                    pending = res
                    onSpin()
                }
            },
            enabled = pending == null,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
        ) {
            Text("🎰 КРУТИТЬ ${formatBablo(kotlin.math.floor(game.money * fraction))}", fontWeight = FontWeight.Black, fontSize = 17.sp)
        }
        Text(
            "Спинов: ${game.slotSpins} · 6️⃣7️⃣: ${game.slotSixSevens} · троек: ${game.slotThrees} · 👑: ${game.slotCrowns}",
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.6f),
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}
