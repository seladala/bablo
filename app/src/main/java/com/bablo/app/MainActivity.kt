package com.bablo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.random.Random

val MONEY_EMOJI = listOf("💸", "💰", "🤑", "💵", "🪙", "💶")
val EMOJI_67 = listOf("6️⃣", "7️⃣", "🤷", "💸", "6️⃣", "7️⃣")
val ULTRA_EMOJI = listOf("💎", "👑", "💰", "🤑", "🔥", "💸", "🏦")
private val FEVER_EMOJI = listOf("6️⃣", "7️⃣", "💥", "⚡", "🔥", "👑", "💎")
private val RAINBOW = listOf(Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Blue, Color.Magenta, Color.Red)

class MainActivity : ComponentActivity() {
    private lateinit var game: BabloGame
    private lateinit var sound: SoundEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = BabloGame(this)
        sound = SoundEngine(this)
        setContent {
            BabloTheme(game) { BabloScreen(game, sound) }
        }
    }

    override fun onPause() {
        super.onPause()
        game.save()
    }

    override fun onDestroy() {
        super.onDestroy()
        sound.release()
    }
}

data class Palette(val main: Color, val accent: Color, val bgTop: Color, val bgBottom: Color)

fun paletteFor(game: BabloGame) = when {
    game.fever -> Palette(Color(0xFFFF1744), Color(0xFFFFEA00), Color(0xFF4A0010), Color(0xFF1A0033))
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

@Composable
private fun BabloScreen(game: BabloGame, sound: SoundEngine) {
    val measurer = rememberTextMeasurer()
    val fx = remember { Fx(measurer) }
    val haptic = LocalHapticFeedback.current

    var buttonTopLeft by remember { mutableStateOf(Offset.Zero) }
    var buttonCenter by remember { mutableStateOf(Offset.Zero) }
    var buttonRadius by remember { mutableFloatStateOf(0f) }
    var screenW by remember { mutableFloatStateOf(1080f) }
    var screenH by remember { mutableFloatStateOf(1920f) }
    var sheetTab by remember { mutableIntStateOf(-1) }
    var showWithdraw by remember { mutableStateOf(false) }
    var pressed by remember { mutableStateOf(false) }
    var pressStart by remember { mutableLongStateOf(0L) }
    var holdCharge by remember { mutableFloatStateOf(0f) }

    sound.enabled = game.soundOn

    fun buzz(strong: Boolean) {
        if (!game.vibrationOn) return
        haptic.performHapticFeedback(if (strong) HapticFeedbackType.LongPress else HapticFeedbackType.TextHandleMove)
    }

    fun onBablo(at: Offset, held: Boolean = false) {
        val r = game.click()
        val emojis = when {
            game.fever -> FEVER_EMOJI
            game.mode67 -> EMOJI_67
            game.ultraBablo -> ULTRA_EMOJI
            else -> MONEY_EMOJI
        }
        val pal = paletteFor(game)
        fx.burst(at, emojis, if (r.crit) 26 else (if (held) 3 else 7) + min(r.combo / 4, 12), if (r.crit) 1.5f else 1f)
        fx.ring(at, pal.main, buttonRadius * 0.9f, 0.45f, 10f)
        if (!held || Random.nextInt(3) == 0) {
            fx.floatingText(
                at,
                (if (r.crit) "КРИТ! " else "") + "+" + formatBablo(r.gain),
                if (r.crit) Color(0xFFFF1744) else pal.accent,
                big = r.crit,
            )
        }
        if (r.crit) {
            fx.kick(30f, 0.9f, Color(0xFFFF1744))
            fx.sparks(at, 30, Color(0xFFFFEA00), 1.3f)
            fx.ring(buttonCenter, Color(0xFFFF1744), buttonRadius * 2.2f, 0.6f, 22f)
            sound.play(Sfx.CRIT)
            buzz(true)
        } else {
            fx.kick(5f + min(r.combo, 40) * 0.3f)
            sound.play(Sfx.COIN, rate = 0.9f + min(r.combo, 60) / 60f, volume = if (held) 0.4f else 0.7f)
            buzz(false)
        }
        if (game.mode67 && game.totalClicks % 67 == 0) {
            fx.burst(buttonCenter, listOf("6️⃣", "7️⃣"), 67, 1.8f)
            fx.floatingText(buttonCenter, "SIX SEVEN!!! 🤷", Color(0xFFFFD600), big = true)
            fx.ring(buttonCenter, Color(0xFFFFD600), screenW, 0.9f, 30f)
            fx.kick(40f, 1f, Color(0xFFB388FF))
            sound.play(Sfx.SIX_SEVEN)
        }
        if (r.fever) {
            fx.burst(buttonCenter, FEVER_EMOJI, 67, 2f)
            fx.confetti(buttonCenter, 80)
            fx.floatingText(buttonCenter, "⚡ ULTRA 6-7 ⚡", Color(0xFFFFEA00), big = true)
            fx.ring(buttonCenter, Color(0xFFFF1744), screenW * 1.2f, 1f, 40f)
            fx.kick(50f, 1f, Color(0xFFFF1744))
            sound.play(Sfx.FEVER)
            buzz(true)
        } else if (r.combo > 0 && r.combo % 25 == 0) {
            fx.floatingText(buttonCenter, "КОМБО x${r.combo} 🔥", Color(0xFFFF9100), big = true)
            fx.sparks(buttonCenter, 40, Color(0xFFFF9100))
        }
        if (r.echo) {
            for (i in 1..3) fx.ring(at, Color(0xFF80D8FF), buttonRadius * (0.6f + 0.5f * i), 0.4f + 0.2f * i, 8f)
            fx.floatingText(at, "🔁 ЭХО x3", Color(0xFF80D8FF))
        }
        r.prize?.let {
            fx.floatingText(buttonCenter, it, Color(0xFFFFD600), big = true)
            fx.confetti(buttonCenter, 40)
            sound.play(Sfx.GOLDEN)
        }
        if (r.goldenSpawned) fx.floatingText(at, "🌯 Шаурма-крит!", Color(0xFFFFD600), big = true)
        if (game.hammamLevel > 0 && Random.nextInt(4) == 0) {
            fx.bubble(at, buttonRadius * 0.3f)
        }
    }

    // Игровой цикл: автобабло, турбо-бульки, зажатие, дождь, частицы.
    LaunchedEffect(Unit) {
        var last = 0L
        var saveAcc = 0f
        var rainAcc = 0f
        var ambientAcc = 0f
        var holdAcc = 0.0
        while (true) {
            withFrameNanos { now ->
                val dt = if (last == 0L) 0f else ((now - last) / 1e9f).coerceAtMost(0.1f)
                last = now
                val tr = game.tick(dt.toDouble())
                if (tr.boiled > 0) {
                    fx.burst(buttonCenter, listOf("♨️", "💨", "🫧", "💰"), 18, 1f)
                    fx.floatingText(buttonCenter, "♨️ Кипение +${formatBablo(tr.boiled)}", Color(0xFFFFCC80), big = true)
                    fx.ring(buttonCenter, Color(0xFFFF7043), buttonRadius * 2f, 0.7f, 18f)
                    sound.play(Sfx.HAMMAM, volume = 0.5f)
                }
                repeat(tr.bulks) {
                    val r = game.click(auto = true)
                    fx.bubble(buttonCenter, buttonRadius)
                    fx.bubble(buttonCenter, buttonRadius)
                    sound.play(Sfx.BUBBLE, rate = 0.8f + Random.nextFloat() * 0.6f, volume = 0.35f)
                    if (Random.nextInt(4) == 0) {
                        fx.floatingText(buttonCenter, "буль +${formatBablo(r.gain)}", Color(0xFF80D8FF))
                    }
                }
                // Зажатие кнопки = ручной турбо
                val heldMs = System.currentTimeMillis() - pressStart
                if (pressed && heldMs > 250) {
                    holdCharge = ((heldMs - 250) / 1500f).coerceAtMost(1f)
                    holdAcc += game.holdRate * (0.5 + holdCharge) * dt
                    while (holdAcc >= 1.0) {
                        holdAcc -= 1.0
                        val a = Random.nextFloat() * 6.2832f
                        val d = Random.nextFloat() * buttonRadius * 0.6f
                        onBablo(buttonCenter + Offset(kotlin.math.cos(a) * d, sin(a) * d), held = true)
                    }
                    if (Random.nextInt(3) == 0) fx.sparks(buttonCenter, 2, Color(0xFF80D8FF), 0.6f)
                } else {
                    holdCharge = 0f
                    holdAcc = 0.0
                }
                if (game.ultraBablo || game.fever) {
                    rainAcc += dt * (if (game.fever) 45f else 22f)
                    while (rainAcc >= 1f) {
                        fx.rain(screenW, if (game.fever) FEVER_EMOJI else ULTRA_EMOJI)
                        rainAcc -= 1f
                    }
                }
                ambientAcc += dt
                if (ambientAcc > 0.7f) {
                    ambientAcc = 0f
                    fx.ambient(screenW, screenH, if (game.mode67) EMOJI_67 else MONEY_EMOJI)
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

    // ---- Фон ----
    val palette = paletteFor(game)
    val inf = rememberInfiniteTransition(label = "bg")
    val hue by inf.animateFloat(0f, 360f, infiniteRepeatable(tween(4000, easing = LinearEasing)), label = "hue")
    val bgTop by animateColorAsState(palette.bgTop, tween(800), label = "bgTop")
    val bgBottom by animateColorAsState(palette.bgBottom, tween(800), label = "bgBottom")

    Box(
        Modifier
            .fillMaxSize()
            .drawBehind {
                if (game.ultraBablo || game.fever) {
                    val h = if (game.fever) (hue * 3f) % 360f else hue
                    drawRect(
                        Brush.verticalGradient(
                            listOf(
                                Color.hsv(h, 0.85f, 0.35f),
                                Color.hsv((h + 90f) % 360f, 0.85f, 0.22f),
                                Color.hsv((h + 180f) % 360f, 0.85f, 0.12f),
                            )
                        )
                    )
                } else {
                    drawRect(Brush.verticalGradient(listOf(bgTop, bgBottom)))
                }
                // Мягкий свет за кнопкой
                drawCircle(
                    Brush.radialGradient(
                        listOf(palette.main.copy(alpha = 0.18f), Color.Transparent),
                        center = buttonCenter,
                        radius = size.width * 0.9f,
                    ),
                    radius = size.width * 0.9f,
                    center = buttonCenter,
                )
            }
            .onGloballyPositioned {
                screenW = it.size.width.toFloat()
                screenH = it.size.height.toFloat()
            }
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
            Spacer(Modifier.height(8.dp))
            StatsPanel(game, onWithdraw = { showWithdraw = true })
            if (game.hammamLevel > 0) {
                Spacer(Modifier.height(6.dp))
                Thermometer(game)
            }
            Spacer(Modifier.height(10.dp))
            ModesRow(
                game,
                onToggle = { emojis, color ->
                    fx.burst(buttonCenter, emojis, 24, 1.3f)
                    fx.ring(buttonCenter, color, screenW, 0.8f, 26f)
                    fx.kick(18f, 0.6f, color)
                    sound.play(Sfx.MODE)
                    buzz(true)
                },
                onDenied = {
                    sound.play(Sfx.DENIED, volume = 0.5f)
                    buzz(false)
                },
            )

            BoxWithConstraints(
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                val size = minOf(maxWidth * 0.86f, maxHeight * 0.86f, 230.dp * game.buttonScale)
                UltimateButton(
                    game = game,
                    palette = palette,
                    pressed = pressed,
                    holdCharge = holdCharge,
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
                                pressStart = System.currentTimeMillis()
                                onBablo(buttonTopLeft + down.position)
                                waitForUpOrCancellation()
                                pressed = false
                            }
                        },
                )
                if (!pressed && game.totalClicks in 1..30) {
                    Text(
                        "зажми для турбо ✊",
                        Modifier.align(Alignment.BottomCenter),
                        color = Color.White.copy(alpha = 0.5f),
                        fontSize = 12.sp,
                    )
                }
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                BottomTab("🏭", "Бизнес", primary = true, Modifier.weight(1f)) { sheetTab = 0 }
                BottomTab("👆", "Кнопка", primary = true, Modifier.weight(1f)) { sheetTab = 1 }
                BottomTab("🧖", "Хаммам", primary = false, Modifier.weight(1f)) { sheetTab = 2 }
                BottomTab("⭐", "Ещё", primary = false, Modifier.weight(0.8f)) { sheetTab = 3 }
            }
            Spacer(Modifier.height(10.dp))
        }

        // Золотой мешок
        game.golden?.let { bag ->
            GoldenBagView(bag, screenW, screenH) {
                val reward = game.catchGolden() ?: return@GoldenBagView
                val at = Offset(bag.xFrac * screenW, bag.yFrac * screenH)
                fx.burst(at, listOf("✨", "💰", "🌟", "🪙"), 40, 1.4f)
                fx.confetti(at, 50)
                fx.ring(at, Color(0xFFFFD600), screenW * 0.8f, 0.8f, 24f)
                fx.floatingText(at, reward.text, Color(0xFFFFD600), big = true)
                fx.kick(20f, 0.7f, Color(0xFFFFD600))
                sound.play(Sfx.GOLDEN)
                buzz(true)
            }
        }

        // Слой эффектов поверх всего
        Canvas(Modifier.fillMaxSize()) {
            val f = fx.frame
            fx.draw(this)
            if (fx.flash > 0f) drawRect(fx.flashColor.copy(alpha = fx.flash * 0.3f))
            if (game.fever) {
                // радужная рамка лихорадки
                val w = 14f + 10f * sin(f * 0.3f)
                drawRect(
                    Brush.sweepGradient(RAINBOW, center),
                    style = Stroke(width = w * 2f),
                )
            }
        }

        AchievementBanner(game, sound, Modifier.align(Alignment.TopCenter))
    }

    if (sheetTab >= 0) {
        BabloSheet(
            game = game,
            initialTab = sheetTab,
            onDismiss = { sheetTab = -1 },
            onBuy = {
                fx.confetti(buttonCenter, 30)
                fx.ring(buttonCenter, palette.main, buttonRadius * 1.6f, 0.5f, 16f)
                sound.play(Sfx.BUY)
                buzz(false)
            },
            onHammamUp = {
                fx.burst(buttonCenter, listOf("🧖", "♨️", "🫧", "💨"), 40, 1.2f)
                fx.kick(15f, 0.8f, Color(0xFFFFCC80))
                sound.play(Sfx.HAMMAM)
                buzz(true)
            },
            onDenied = {
                sound.play(Sfx.DENIED, volume = 0.6f)
                buzz(true)
            },
        )
    }

    if (showWithdraw) {
        WithdrawDialog(
            game = game,
            onDismiss = { showWithdraw = false },
            onWithdraw = {
                val gained = game.withdraw()
                showWithdraw = false
                if (gained > 0) {
                    fx.confetti(Offset(screenW / 2, screenH), 150)
                    fx.burst(buttonCenter, listOf("💳", "🏦", "💸", "💰", "6️⃣", "7️⃣"), 80, 2f)
                    fx.ring(buttonCenter, Color.White, screenW * 1.5f, 1.2f, 50f)
                    fx.floatingText(buttonCenter, "+$gained ЖЕТОНОВ 67", Color(0xFFFFD600), big = true)
                    fx.kick(60f, 1f, Color(0xFFFFD600))
                    sound.play(Sfx.WITHDRAW)
                    buzz(true)
                }
            },
            onDenied = { sound.play(Sfx.DENIED, volume = 0.6f) },
        )
    }

    game.offlineGain?.let { gain ->
        AlertDialog(
            onDismissRequest = { game.offlineGain = null },
            icon = { Text("😴", fontSize = 36.sp) },
            title = { Text("Пока тебя не было…") },
            text = {
                Text(
                    "Генераторы натыкали +${formatBablo(gain)} бабла " +
                        "(${(game.offlineRate * 100).toInt()}% от обычного дохода, максимум 8 часов).",
                )
            },
            confirmButton = {
                Button(onClick = {
                    game.offlineGain = null
                    fx.burst(buttonCenter, MONEY_EMOJI, 30, 1.3f)
                    sound.play(Sfx.BUY)
                }) { Text("Забрать 💰") }
            },
        )
    }

    // Сохраняем при уходе с экрана
    DisposableEffect(Unit) { onDispose { game.save() } }
}

@Composable
private fun GoldenBagView(bag: GoldenBag, screenW: Float, screenH: Float, onCatch: () -> Unit) {
    val inf = rememberInfiniteTransition(label = "bag")
    val bob by inf.animateFloat(-14f, 14f, infiniteRepeatable(tween(600), RepeatMode.Reverse), label = "bob")
    val spin by inf.animateFloat(-15f, 15f, infiniteRepeatable(tween(450), RepeatMode.Reverse), label = "spin")
    val glow by inf.animateFloat(0.4f, 1f, infiniteRepeatable(tween(300), RepeatMode.Reverse), label = "glow")
    Box(
        Modifier
            .offset { IntOffset((bag.xFrac * screenW).roundToInt() - 90, (bag.yFrac * screenH + bob).roundToInt() - 90) }
            .size(64.dp)
            .graphicsLayer { rotationZ = spin }
            .drawBehind {
                drawCircle(
                    Brush.radialGradient(listOf(Color(0xFFFFD600).copy(alpha = glow), Color.Transparent)),
                    radius = size.minDimension * 0.8f,
                )
            }
            .clip(CircleShape)
            .clickable(onClick = onCatch),
        contentAlignment = Alignment.Center,
    ) {
        Text("💰", fontSize = 42.sp)
    }
}

@Composable
private fun AchievementBanner(game: BabloGame, sound: SoundEngine, modifier: Modifier) {
    val current = game.pendingAchievements.firstOrNull()
    var shown by remember { mutableStateOf<Achievement?>(null) }
    LaunchedEffect(current) {
        if (current != null) {
            shown = current
            sound.play(Sfx.ACHIEVEMENT)
            kotlinx.coroutines.delay(2600)
            game.pendingAchievements.remove(current)
        }
    }
    AnimatedVisibility(
        visible = current != null,
        modifier = modifier
            .statusBarsPadding()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
        enter = slideInVertically { -it } + fadeIn(),
        exit = slideOutVertically { -it } + fadeOut(),
    ) {
        val a = shown ?: return@AnimatedVisibility
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFF212121),
            border = BorderStroke(2.dp, Color(0xFFFFD600)),
            shadowElevation = 12.dp,
        ) {
            Row(Modifier.padding(horizontal = 16.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(a.emoji, fontSize = 30.sp)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("🏆 Достижение!", color = Color(0xFFFFD600), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(a.title, color = Color.White, fontWeight = FontWeight.Black)
                    Text(a.description, color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
private fun StatsPanel(game: BabloGame, onWithdraw: () -> Unit) {
    ElevatedCard(
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.Black.copy(alpha = 0.35f)),
    ) {
        Box(Modifier.fillMaxWidth()) {
            Row(Modifier.align(Alignment.TopEnd)) {
                TextButton(onClick = { game.soundOn = !game.soundOn }) {
                    Text(if (game.soundOn) "🔊" else "🔇", fontSize = 18.sp)
                }
                TextButton(onClick = { game.vibrationOn = !game.vibrationOn }) {
                    Text(if (game.vibrationOn) "📳" else "📴", fontSize = 18.sp)
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    when {
                        game.fever -> "⚡ ULTRA 6-7 ⚡ ${formatTime(game.feverLeftMs)}"
                        game.mode67 -> "УЛЬТИМАТИВНОЕ БАБЛО · 67"
                        else -> "УЛЬТИМАТИВНОЕ БАБЛО"
                    },
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    letterSpacing = 2.sp,
                )
                Text(
                    "💰 " + formatBablo(game.money),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(shadow = Shadow(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f), blurRadius = 24f)),
                    maxLines = 1,
                )
                Text(
                    "за тык ${formatBablo(game.perClick)} · в сек ${formatBablo(game.perSecond)} · x${formatBablo(game.multiplier)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (game.tokens > 0) {
                        Text("🎟️ ${game.tokens} жетонов 67 ", fontSize = 12.sp, color = Color(0xFFFFD600))
                    }
                    if (game.ultraBablo) Text("👑 x5 ", fontSize = 12.sp, color = Color(0xFFFFD600), fontWeight = FontWeight.Bold)
                    if (game.frenzy) Text("🤪 БЕЗУМИЕ x7 ", fontSize = 12.sp, color = Color(0xFFFFD600), fontWeight = FontWeight.Bold)
                    if (game.combo > 1) {
                        Text(
                            "🔥 x${game.combo} (+${(game.comboBonus * 100).toInt()}%)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF9100),
                        )
                    }
                }
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            "💳 Вывод от 68 квинтиллиардов",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.7f),
                        )
                        LinearProgressIndicator(
                            progress = { game.withdrawProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    FilledTonalButton(
                        onClick = onWithdraw,
                        contentPadding = PaddingValues(horizontal = 12.dp),
                        modifier = Modifier.height(34.dp),
                    ) {
                        Text(if (game.canWithdraw) "ВЫВЕСТИ" else "Вывод", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun ModesRow(game: BabloGame, onToggle: (List<String>, Color) -> Unit, onDenied: () -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        val m67Status = when {
            game.mode67 -> "⏳ ${formatTime(game.mode67LeftMs)}"
            game.mode67ReadyInMs == 0L -> "ГОТОВ!"
            else -> "через ${formatTime(game.mode67ReadyInMs)}"
        }
        ModeCard(
            emoji = "6️⃣7️⃣", title = "Режим 67", status = m67Status,
            active = game.mode67, ready = !game.mode67 && game.mode67ReadyInMs == 0L,
            progress = when {
                game.mode67 -> game.mode67LeftMs / game.mode67DurationMs.toFloat()
                else -> 1f - game.mode67ReadyInMs / game.mode67CooldownMs.toFloat()
            },
            modifier = Modifier.weight(1f),
        ) {
            if (game.activate67()) onToggle(listOf("6️⃣", "7️⃣"), Color(0xFFB388FF)) else onDenied()
        }
        ModeCard(
            emoji = "👑", title = "Ультра-бабло",
            status = when {
                game.ultraBablo -> "⏳ ${formatTime(game.ultraLeftMs)}"
                game.ultraReady -> "ГОТОВ!"
                else -> "⚡ ${game.ultraCharge}/${BabloGame.ULTRA_CHARGE}"
            },
            active = game.ultraBablo, ready = game.ultraReady,
            progress = if (game.ultraBablo) game.ultraLeftMs / BabloGame.ULTRA_DURATION.toFloat()
            else game.ultraCharge / BabloGame.ULTRA_CHARGE.toFloat(),
            modifier = Modifier.weight(1f),
        ) {
            if (game.activateUltra()) onToggle(ULTRA_EMOJI, Color(0xFFFFD600)) else onDenied()
        }
        val turboStatus = when {
            game.turbo -> "⏳ ${formatTime(game.turboLeftMs)}"
            game.turboReadyInMs == 0L -> "ГОТОВ!"
            else -> "через ${formatTime(game.turboReadyInMs)}"
        }
        ModeCard(
            emoji = "🫧", title = "Турбо-выбулькиватель", status = turboStatus,
            active = game.turbo, ready = !game.turbo && game.turboReadyInMs == 0L,
            progress = when {
                game.turbo -> game.turboLeftMs / game.turboDurationMs.toFloat()
                else -> 1f - game.turboReadyInMs / game.turboCooldownMs.toFloat()
            },
            modifier = Modifier.weight(1f),
        ) {
            if (game.activateTurbo()) onToggle(listOf("🫧", "💧"), Color(0xFF40C4FF)) else onDenied()
        }
    }
}

@Composable
private fun ModeCard(
    emoji: String,
    title: String,
    status: String,
    active: Boolean,
    ready: Boolean,
    progress: Float,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    val inf = rememberInfiniteTransition(label = "mode")
    val blink by inf.animateFloat(0.4f, 1f, infiniteRepeatable(tween(500), RepeatMode.Reverse), label = "blink")
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (active) MaterialTheme.colorScheme.primary.copy(alpha = 0.28f) else Color.Black.copy(alpha = 0.3f),
        ),
        border = when {
            active -> BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
            ready -> BorderStroke(2.dp, MaterialTheme.colorScheme.secondary.copy(alpha = blink))
            else -> null
        },
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 6.dp),
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
            Text(
                status,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = if (ready) MaterialTheme.colorScheme.secondary else Color.White.copy(alpha = 0.7f),
                maxLines = 1,
            )
            Spacer(Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
            )
        }
    }
}

@Composable
private fun UltimateButton(game: BabloGame, palette: Palette, pressed: Boolean, holdCharge: Float, modifier: Modifier) {
    val inf = rememberInfiniteTransition(label = "btn")
    val pulse by inf.animateFloat(
        1f, 1.05f,
        infiniteRepeatable(tween(if (game.turbo || game.fever) 160 else 700), RepeatMode.Reverse),
        label = "pulse",
    )
    val ringAngle by inf.animateFloat(
        0f, 360f,
        infiniteRepeatable(tween(if (game.ultraBablo || game.fever) 800 else 3000, easing = LinearEasing)),
        label = "ring",
    )
    val hue by inf.animateFloat(0f, 360f, infiniteRepeatable(tween(2000, easing = LinearEasing)), label = "hue")
    // «Six-seven» покачивание
    val wobble by inf.animateFloat(-6.7f, 6.7f, infiniteRepeatable(tween(167), RepeatMode.Reverse), label = "wobble")
    val pressScale by animateFloatAsState(
        if (pressed) 0.86f else 1f,
        spring(dampingRatio = 0.3f, stiffness = 700f),
        label = "press",
    )

    val core = when {
        game.fever -> listOf(Color.White, Color.hsv(hue, 1f, 1f), Color.hsv((hue + 120f) % 360f, 1f, 0.5f))
        game.skin == Skin.RAINBOW -> listOf(Color.White, Color.hsv(hue, 0.9f, 1f), Color.hsv((hue + 180f) % 360f, 0.9f, 0.5f))
        game.mode67 && game.skin == Skin.CLASSIC -> listOf(Color(0xFFFFE57F), Color(0xFFFFAB00), Color(0xFF6A1B9A))
        else -> game.skin.colors
    }
    val ring = if (game.ultraBablo || game.fever) RAINBOW else listOf(palette.main, palette.accent, Color.White, palette.main)
    val comboFrac = (game.combo % 67) / 67f

    Box(
        modifier.graphicsLayer {
            val s = pulse * pressScale * (1f + holdCharge * 0.08f)
            scaleX = s
            scaleY = s
            rotationZ = if (game.mode67 || game.fever) wobble else 0f
        },
        contentAlignment = Alignment.Center,
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val r = size.minDimension / 2f
            drawCircle(
                Brush.radialGradient(
                    listOf(palette.main.copy(alpha = 0.55f + holdCharge * 0.4f), Color.Transparent),
                    center = center,
                    radius = r * 1.3f,
                ),
                radius = r * 1.3f,
            )
            rotate(ringAngle) {
                drawCircle(Brush.sweepGradient(ring, center), radius = r * 0.97f, style = Stroke(width = r * 0.07f))
            }
            // Шкала комбо до ULTRA 6-7
            if (comboFrac > 0f) {
                val rr = r * 1.08f
                drawArc(
                    Color(0xFFFF9100),
                    startAngle = -90f,
                    sweepAngle = 360f * comboFrac,
                    useCenter = false,
                    topLeft = Offset(center.x - rr, center.y - rr),
                    size = Size(rr * 2, rr * 2),
                    style = Stroke(width = r * 0.04f, cap = StrokeCap.Round),
                )
            }
            // Заряд зажатия
            if (holdCharge > 0f) {
                val rr = r * 1.15f
                drawArc(
                    Color(0xFF80D8FF),
                    startAngle = -90f,
                    sweepAngle = 360f * holdCharge,
                    useCenter = false,
                    topLeft = Offset(center.x - rr, center.y - rr),
                    size = Size(rr * 2, rr * 2),
                    style = Stroke(width = r * 0.05f, cap = StrokeCap.Round),
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
                when {
                    game.fever -> {
                        Text("ULTRA", fontSize = 30.sp, fontWeight = FontWeight.Black, color = Color.White, style = TextStyle(shadow = shadow))
                        Text("6-7", fontSize = 76.sp, fontWeight = FontWeight.Black, color = Color.White, style = TextStyle(shadow = shadow))
                    }
                    game.mode67 -> {
                        Text("67", fontSize = 84.sp, fontWeight = FontWeight.Black, color = Color.White, style = TextStyle(shadow = shadow))
                        Text("БАБЛО", fontSize = 26.sp, fontWeight = FontWeight.Black, color = Color.White, style = TextStyle(shadow = shadow))
                    }
                    else -> {
                        Text(
                            "БАБЛО",
                            fontSize = 46.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            letterSpacing = 2.sp,
                            style = TextStyle(shadow = shadow),
                        )
                        Text(
                            when {
                                holdCharge > 0f -> "ТУРБО ${(holdCharge * 100).toInt()}%"
                                game.ultraBablo -> "УЛЬТРА"
                                else -> "ЖМИ"
                            },
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White.copy(alpha = 0.85f),
                            letterSpacing = 4.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomTab(emoji: String, label: String, primary: Boolean, modifier: Modifier, onClick: () -> Unit) {
    val content: @Composable () -> Unit = {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(emoji, fontSize = 18.sp)
            Text(label, fontSize = 11.sp, fontWeight = FontWeight.Bold, maxLines = 1)
        }
    }
    val shape = RoundedCornerShape(18.dp)
    val pad = PaddingValues(horizontal = 2.dp, vertical = 4.dp)
    if (primary) {
        Button(onClick = onClick, modifier = modifier.height(58.dp), shape = shape, contentPadding = pad) { content() }
    } else {
        FilledTonalButton(onClick = onClick, modifier = modifier.height(58.dp), shape = shape, contentPadding = pad) { content() }
    }
}

@Composable
private fun Thermometer(game: BabloGame) {
    val t = game.temperature
    val frac = (t / game.maxTemperature).coerceIn(0f, 1f)
    val color = when {
        t >= 90f -> Color(0xFFFF1744)
        t >= 67f -> Color(0xFFFF6D00)
        else -> Color(0xFFFFB74D)
    }
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.Black.copy(alpha = 0.3f))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("♨️ ${t.toInt()}°", fontWeight = FontWeight.Bold, color = color, fontSize = 13.sp, modifier = Modifier.width(64.dp))
        LinearProgressIndicator(
            progress = { frac },
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = color,
        )
        Spacer(Modifier.width(8.dp))
        Text(
            "тык x${"%.2f".format(game.tempMult)}" + if (game.hammamHas(6) && t >= 90f) " · КИПИТ" else "",
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.8f),
        )
    }
}
