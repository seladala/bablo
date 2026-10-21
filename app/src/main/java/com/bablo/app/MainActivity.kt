package com.bablo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.min
import kotlin.random.Random

private val MONEY_EMOJI = listOf("💸", "💰", "🤑", "💵", "🪙", "💶")
private val EMOJI_67 = listOf("6️⃣", "7️⃣", "🤷", "💸", "6️⃣", "7️⃣")
private val ULTRA_EMOJI = listOf("💎", "👑", "💰", "🤑", "🔥", "💸", "🏦")

class MainActivity : ComponentActivity() {
    private lateinit var game: BabloGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = BabloGame(this)
        setContent {
            BabloTheme(game) { BabloScreen(game) }
        }
    }

    override fun onPause() {
        super.onPause()
        game.save()
    }
}

private data class Palette(val main: Color, val accent: Color, val bgTop: Color, val bgBottom: Color)

private fun paletteFor(game: BabloGame) = when {
    game.mode67 && game.ultraBablo -> Palette(Color(0xFFFFD600), Color(0xFFE040FB), Color(0xFF2A0845), Color(0xFF3E2723))
    game.mode67 -> Palette(Color(0xFFB388FF), Color(0xFFFFD600), Color(0xFF1A0033), Color(0xFF0D0D2B))
    game.ultraBablo -> Palette(Color(0xFFFFD600), Color(0xFFFF6D00), Color(0xFF3E2000), Color(0xFF1A0F00))
    game.turbo -> Palette(Color(0xFF40C4FF), Color(0xFF00E676), Color(0xFF00213A), Color(0xFF001A12))
    else -> Palette(Color(0xFF00E676), Color(0xFFFFD600), Color(0xFF0B2A14), Color(0xFF050F08))
}

@Composable
private fun BabloTheme(game: BabloGame, content: @Composable () -> Unit) {
    val p = paletteFor(game)
    val primary by animateColorAsState(p.main, tween(600), label = "primary")
    val secondary by animateColorAsState(p.accent, tween(600), label = "secondary")
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = primary,
            onPrimary = Color.Black,
            secondary = secondary,
            onSecondary = Color.Black,
            tertiary = Color(0xFF80D8FF),
            surface = Color(0xFF121A15),
            surfaceContainerLow = Color(0xFF16201A),
            surfaceContainer = Color(0xFF1A251E),
            surfaceContainerHigh = Color(0xFF1F2B23),
        ),
        content = content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BabloScreen(game: BabloGame) {
    val measurer = rememberTextMeasurer()
    val fx = remember { Fx(measurer) }
    val haptic = LocalHapticFeedback.current
    val palette = paletteFor(game)

    var buttonTopLeft by remember { mutableStateOf(Offset.Zero) }
    var buttonCenter by remember { mutableStateOf(Offset.Zero) }
    var buttonRadius by remember { mutableFloatStateOf(0f) }
    var screenWidth by remember { mutableFloatStateOf(1080f) }
    var showShop by remember { mutableStateOf(false) }
    var pressed by remember { mutableStateOf(false) }

    // Игровой цикл: автобабло, турбо-бульки, денежный дождь, частицы.
    LaunchedEffect(Unit) {
        var last = 0L
        var saveAcc = 0f
        var rainAcc = 0f
        while (true) {
            withFrameNanos { now ->
                val dt = if (last == 0L) 0f else ((now - last) / 1e9f).coerceAtMost(0.1f)
                last = now
                repeat(game.tick(dt.toDouble())) {
                    val r = game.click(auto = true)
                    fx.bubble(buttonCenter, buttonRadius)
                    fx.bubble(buttonCenter, buttonRadius)
                    if (Random.nextInt(4) == 0) {
                        fx.floatingText(buttonCenter, "буль +${formatBablo(r.gain)}", Color(0xFF80D8FF))
                    }
                }
                if (game.ultraBablo) {
                    rainAcc += dt * 22f
                    while (rainAcc >= 1f) {
                        fx.rain(screenWidth, ULTRA_EMOJI)
                        rainAcc -= 1f
                    }
                }
                fx.step(dt)
                saveAcc += dt
                if (saveAcc > 5f) {
                    game.save()
                    saveAcc = 0f
                }
            }
        }
    }

    fun onBablo(at: Offset) {
        val r = game.click()
        val emojis = when {
            game.mode67 -> EMOJI_67
            game.ultraBablo -> ULTRA_EMOJI
            else -> MONEY_EMOJI
        }
        fx.burst(at, emojis, if (r.crit) 26 else 7 + min(r.combo / 4, 12), if (r.crit) 1.5f else 1f)
        fx.floatingText(
            at,
            (if (r.crit) "КРИТ! " else "") + "+" + formatBablo(r.gain),
            if (r.crit) Color(0xFFFF1744) else paletteFor(game).accent,
            big = r.crit,
        )
        if (r.crit) {
            fx.kick(30f, 0.9f)
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        } else {
            fx.kick(5f + min(r.combo, 40) * 0.3f)
            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
        }
        if (game.mode67 && game.totalClicks % 67 == 0) {
            fx.burst(buttonCenter, listOf("6️⃣", "7️⃣"), 67, 1.8f)
            fx.floatingText(buttonCenter, "SIX SEVEN!!! 🤷", Color(0xFFFFD600), big = true)
            fx.kick(40f, 1f)
        }
        if (r.combo > 0 && r.combo % 25 == 0) {
            fx.floatingText(buttonCenter, "КОМБО x${r.combo} 🔥", Color(0xFFFF9100), big = true)
        }
    }

    // ---- Фон ----
    val inf = rememberInfiniteTransition(label = "bg")
    val hue by inf.animateFloat(0f, 360f, infiniteRepeatable(tween(4000, easing = LinearEasing)), label = "hue")
    val bgTop by animateColorAsState(palette.bgTop, tween(800), label = "bgTop")
    val bgBottom by animateColorAsState(palette.bgBottom, tween(800), label = "bgBottom")

    Box(
        Modifier
            .fillMaxSize()
            .drawBehind {
                if (game.ultraBablo) {
                    drawRect(
                        Brush.verticalGradient(
                            listOf(
                                Color.hsv(hue, 0.85f, 0.35f),
                                Color.hsv((hue + 90f) % 360f, 0.85f, 0.22f),
                                Color.hsv((hue + 180f) % 360f, 0.85f, 0.12f),
                            )
                        )
                    )
                } else {
                    drawRect(Brush.verticalGradient(listOf(bgTop, bgBottom)))
                }
            }
            .onGloballyPositioned { screenWidth = it.size.width.toFloat() }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .graphicsLayer {
                    @Suppress("UNUSED_VARIABLE") val f = fx.frame
                    val s = fx.shake
                    translationX = (Random.nextFloat() - 0.5f) * s
                    translationY = (Random.nextFloat() - 0.5f) * s
                }
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(12.dp))
            StatsPanel(game)
            Spacer(Modifier.height(12.dp))
            ModesRow(game, onToggle = { fx.burst(buttonCenter, it, 20, 1.2f); fx.kick(15f, 0.5f) })

            BoxWithConstraints(
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                val size = minOf(maxWidth * 0.88f, maxHeight * 0.88f, 230.dp * game.buttonScale)
                UltimateButton(
                    game = game,
                    palette = palette,
                    pressed = pressed,
                    modifier = Modifier
                        .size(size)
                        .onGloballyPositioned {
                            buttonTopLeft = it.positionInRoot()
                            buttonRadius = it.size.width / 2f
                            buttonCenter = buttonTopLeft + Offset(buttonRadius, buttonRadius)
                        }
                        .pointerInput(Unit) {
                            awaitEachGesture {
                                val down = awaitFirstDown()
                                pressed = true
                                onBablo(buttonTopLeft + down.position)
                                waitForUpOrCancellation()
                                pressed = false
                            }
                        },
                )
            }

            Button(
                onClick = { showShop = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp),
            ) {
                Text("🛒  Улучшения кнопки", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(12.dp))
        }

        // Слой эффектов поверх всего
        Canvas(Modifier.fillMaxSize()) {
            @Suppress("UNUSED_VARIABLE") val f = fx.frame
            fx.draw(this)
            if (fx.flash > 0f) drawRect(Color.White.copy(alpha = fx.flash * 0.3f))
        }
    }

    if (showShop) {
        ModalBottomSheet(
            onDismissRequest = { showShop = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        ) {
            ShopSheet(game)
        }
    }
}

@Composable
private fun StatsPanel(game: BabloGame) {
    ElevatedCard(
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.Black.copy(alpha = 0.35f)),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "УЛЬТИМАТИВНОЕ БАБЛО" + if (game.mode67) " · 67" else "",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary,
                letterSpacing = 2.sp,
            )
            Text(
                "💰 " + formatBablo(game.money),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary,
                style = TextStyle(shadow = Shadow(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f), blurRadius = 24f)),
                maxLines = 1,
            )
            Text(
                "за тык ${formatBablo(game.perClick)} · в сек ${formatBablo(game.perSecond)} · x${formatBablo(game.multiplier)}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
            )
            if (game.combo > 1) {
                Text(
                    "🔥 КОМБО x${game.combo}  (+${(game.comboBonus * 100).toInt()}%)",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF9100),
                )
            }
        }
    }
}

@Composable
private fun ModesRow(game: BabloGame, onToggle: (List<String>) -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ModeCard("6️⃣7️⃣", "Режим 67", "x67", game.mode67, Modifier.weight(1f)) {
            game.mode67 = it
            if (it) onToggle(listOf("6️⃣", "7️⃣"))
        }
        ModeCard("👑", "Ультра-бабло", "x10 + дождь", game.ultraBablo, Modifier.weight(1f)) {
            game.ultraBablo = it
            if (it) onToggle(ULTRA_EMOJI)
        }
        ModeCard("🫧", "Турбо-выбулькиватель", "12 буль/сек", game.turbo, Modifier.weight(1f)) {
            game.turbo = it
            if (it) onToggle(listOf("🫧", "💧"))
        }
    }
}

@Composable
private fun ModeCard(
    emoji: String,
    title: String,
    hint: String,
    checked: Boolean,
    modifier: Modifier,
    onChange: (Boolean) -> Unit,
) {
    Card(
        onClick = { onChange(!checked) },
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (checked) MaterialTheme.colorScheme.primary.copy(alpha = 0.25f) else Color.Black.copy(alpha = 0.3f),
        ),
        border = if (checked) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(emoji, fontSize = 22.sp)
            Text(
                title,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                color = Color.White,
            )
            Text(hint, fontSize = 10.sp, color = Color.White.copy(alpha = 0.6f), maxLines = 1)
            Switch(checked = checked, onCheckedChange = onChange)
        }
    }
}

@Composable
private fun UltimateButton(game: BabloGame, palette: Palette, pressed: Boolean, modifier: Modifier) {
    val inf = rememberInfiniteTransition(label = "btn")
    val pulse by inf.animateFloat(
        1f, 1.05f,
        infiniteRepeatable(tween(if (game.turbo) 180 else 700), RepeatMode.Reverse),
        label = "pulse",
    )
    val ringAngle by inf.animateFloat(
        0f, 360f,
        infiniteRepeatable(tween(if (game.ultraBablo) 900 else 3000, easing = LinearEasing)),
        label = "ring",
    )
    // «Six-seven» покачивание руками ⚖️
    val wobble by inf.animateFloat(-6.7f, 6.7f, infiniteRepeatable(tween(167), RepeatMode.Reverse), label = "wobble")
    val pressScale by animateFloatAsState(
        if (pressed) 0.86f else 1f,
        spring(dampingRatio = 0.3f, stiffness = 700f),
        label = "press",
    )

    val core = when {
        game.mode67 -> listOf(Color(0xFFFFE57F), Color(0xFFFFAB00), Color(0xFF6A1B9A))
        game.ultraBablo -> listOf(Color(0xFFFFFDE7), Color(0xFFFFD600), Color(0xFFE65100))
        game.turbo -> listOf(Color(0xFFE1F5FE), Color(0xFF40C4FF), Color(0xFF01579B))
        else -> listOf(Color(0xFFB9F6CA), Color(0xFF00E676), Color(0xFF1B5E20))
    }
    val ring = if (game.ultraBablo) {
        listOf(Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Blue, Color.Magenta, Color.Red)
    } else {
        listOf(palette.main, palette.accent, Color.White, palette.main)
    }

    Box(
        modifier
            .graphicsLayer {
                val s = pulse * pressScale
                scaleX = s
                scaleY = s
                rotationZ = if (game.mode67) wobble else 0f
            },
        contentAlignment = Alignment.Center,
    ) {
        // Свечение + вращающееся кольцо
        Canvas(Modifier.fillMaxSize()) {
            val r = size.minDimension / 2f
            drawCircle(
                Brush.radialGradient(
                    listOf(palette.main.copy(alpha = 0.55f), Color.Transparent),
                    center = center,
                    radius = r * 1.25f,
                ),
                radius = r * 1.25f,
            )
            rotate(ringAngle) {
                drawCircle(
                    Brush.sweepGradient(ring, center),
                    radius = r * 0.97f,
                    style = Stroke(width = r * 0.07f),
                )
            }
        }
        Box(
            Modifier
                .fillMaxSize(0.86f)
                .clip(CircleShape)
                .background(Brush.radialGradient(core))
                .border(4.dp, Color.White.copy(alpha = 0.5f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val shadow = Shadow(Color.Black.copy(alpha = 0.6f), Offset(0f, 6f), 12f)
                if (game.mode67) {
                    Text("67", fontSize = 84.sp, fontWeight = FontWeight.Black, color = Color.White, style = TextStyle(shadow = shadow))
                    Text("БАБЛО", fontSize = 26.sp, fontWeight = FontWeight.Black, color = Color.White, style = TextStyle(shadow = shadow))
                } else {
                    Text(
                        "БАБЛО",
                        fontSize = 46.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        letterSpacing = 2.sp,
                        style = TextStyle(shadow = shadow),
                    )
                    Text(
                        if (game.ultraBablo) "УЛЬТРА" else "ЖМИ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.85f),
                        letterSpacing = 6.sp,
                    )
                }
            }
        }
    }
}

@Composable
private fun ShopSheet(game: BabloGame) {
    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Text("Улучшения кнопки", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black)
            Text("💰 " + formatBablo(game.money), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        items(Upgrade.entries) { UpgradeRow(game, it) }
        item { HammamRow(game) }
    }
}

@Composable
private fun UpgradeRow(game: BabloGame, u: Upgrade) {
    val lvl = game.level(u)
    val maxed = lvl >= u.maxLevel
    ElevatedCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(u.emoji, fontSize = 32.sp)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text("${u.title}  ·  ур. $lvl", fontWeight = FontWeight.Bold)
                Text(u.description, style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.7f))
            }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = { game.buy(u) },
                enabled = game.canBuy(u),
                contentPadding = PaddingValues(horizontal = 14.dp),
            ) {
                Text(if (maxed) "МАКС" else formatBablo(game.cost(u)), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun HammamRow(game: BabloGame) {
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
                Text("🧖", fontSize = 32.sp)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Хаммам  ·  легендарное", fontWeight = FontWeight.Bold, color = Color(0xFFFFCC80))
                    Text(
                        "+∞ к расслабону, x∞ бабла. Цена: ∞",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.7f),
                    )
                }
                Spacer(Modifier.width(8.dp))
                OutlinedButton(
                    onClick = {
                        message = game.tryBuyHammam()
                        scope.launch {
                            for (x in listOf(24f, -24f, 18f, -18f, 10f, -10f, 0f)) shake.animateTo(x, tween(40))
                        }
                    },
                    border = BorderStroke(1.dp, Color(0xFFFF5252)),
                    contentPadding = PaddingValues(horizontal = 12.dp),
                ) {
                    Text("🔒 НЕЛЬЗЯ", color = Color(0xFFFF5252), fontWeight = FontWeight.Bold)
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
