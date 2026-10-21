package com.bablo.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

private const val KIND_TEXT = 0
private const val KIND_SPARK = 1
private const val KIND_CONFETTI = 2

private class Particle(
    val layout: TextLayoutResult?,
    val kind: Int,
    val color: Color,
    var x: Float,
    var y: Float,
    var vx: Float,
    var vy: Float,
    val gravity: Float,
    var rot: Float,
    val vrot: Float,
    val scale: Float,
    val life: Float,
    val wobble: Float = 0f,
    val alphaMul: Float = 1f,
) {
    var age = 0f
}

private class Ring(val x: Float, val y: Float, val color: Color, val maxR: Float, val life: Float, val width: Float) {
    var age = 0f
}

private val CONFETTI_COLORS = listOf(
    Color(0xFFFFD600), Color(0xFF00E676), Color(0xFFFF4081), Color(0xFF40C4FF), Color(0xFFE040FB), Color(0xFFFF9100),
)

/** Движок частиц: эмодзи-бабло, пузыри, цифры, искры, конфетти и ударные волны. */
class Fx(private val measurer: TextMeasurer) {
    private val particles = ArrayList<Particle>(800)
    private val rings = ArrayList<Ring>(64)
    private val emojiCache = HashMap<String, TextLayoutResult>()

    /** Читается в draw-лямбде, чтобы Canvas перерисовывался каждый кадр. */
    var frame by mutableLongStateOf(0L)
        private set

    var shake = 0f
        private set
    var flash = 0f
        private set
    var flashColor = Color.White
        private set

    private fun emoji(s: String) = emojiCache.getOrPut(s) {
        measurer.measure(s, TextStyle(fontSize = 34.sp))
    }

    private fun add(p: Particle) {
        if (particles.size > 900) particles.removeAt(0)
        particles += p
    }

    fun burst(at: Offset, emojis: List<String>, count: Int, power: Float = 1f) {
        repeat(count) {
            val a = Random.nextFloat() * 6.2832f
            val speed = (600f + Random.nextFloat() * 900f) * power
            add(
                Particle(
                    layout = emoji(emojis.random()), kind = KIND_TEXT, color = Color.Unspecified,
                    x = at.x, y = at.y,
                    vx = cos(a) * speed, vy = sin(a) * speed - 500f,
                    gravity = 2200f,
                    rot = Random.nextFloat() * 360f, vrot = Random.nextFloat() * 720f - 360f,
                    scale = 0.6f + Random.nextFloat() * 0.8f,
                    life = 1.1f + Random.nextFloat() * 0.6f,
                )
            )
        }
    }

    fun floatingText(at: Offset, text: String, color: Color, big: Boolean = false) {
        val layout = measurer.measure(
            text,
            TextStyle(fontSize = if (big) 38.sp else 24.sp, fontWeight = FontWeight.Black, color = color),
        )
        add(
            Particle(
                layout = layout, kind = KIND_TEXT, color = color,
                x = at.x + Random.nextFloat() * 160f - 80f, y = at.y - 60f,
                vx = Random.nextFloat() * 80f - 40f, vy = if (big) -380f else -520f,
                gravity = 300f, rot = 0f, vrot = 0f,
                scale = 1f, life = if (big) 1.8f else 1.2f,
            )
        )
    }

    fun bubble(at: Offset, radius: Float) {
        val a = Random.nextFloat() * 6.2832f
        add(
            Particle(
                layout = emoji(if (Random.nextInt(5) == 0) "💧" else "🫧"), kind = KIND_TEXT, color = Color.Unspecified,
                x = at.x + cos(a) * radius * 0.7f, y = at.y + sin(a) * radius * 0.7f,
                vx = Random.nextFloat() * 120f - 60f, vy = -350f - Random.nextFloat() * 400f,
                gravity = -250f, rot = 0f, vrot = 0f,
                scale = 0.4f + Random.nextFloat() * 0.7f,
                life = 1.6f + Random.nextFloat(),
                wobble = 6f + Random.nextFloat() * 6f,
            )
        )
    }

    fun rain(width: Float, emojis: List<String>) {
        add(
            Particle(
                layout = emoji(emojis.random()), kind = KIND_TEXT, color = Color.Unspecified,
                x = Random.nextFloat() * width, y = -80f,
                vx = Random.nextFloat() * 100f - 50f, vy = 300f + Random.nextFloat() * 500f,
                gravity = 400f,
                rot = Random.nextFloat() * 360f, vrot = Random.nextFloat() * 300f - 150f,
                scale = 0.5f + Random.nextFloat() * 0.6f,
                life = 4f,
            )
        )
    }

    /** Полупрозрачные купюры, медленно всплывающие на фоне. */
    fun ambient(width: Float, height: Float, emojis: List<String>) {
        add(
            Particle(
                layout = emoji(emojis.random()), kind = KIND_TEXT, color = Color.Unspecified,
                x = Random.nextFloat() * width, y = height + 60f,
                vx = 0f, vy = -60f - Random.nextFloat() * 80f,
                gravity = 0f,
                rot = Random.nextFloat() * 360f, vrot = Random.nextFloat() * 40f - 20f,
                scale = 0.5f + Random.nextFloat() * 0.9f,
                life = 14f,
                wobble = 0.5f + Random.nextFloat(),
                alphaMul = 0.18f,
            )
        )
    }

    fun sparks(at: Offset, count: Int, color: Color, power: Float = 1f) {
        repeat(count) {
            val a = Random.nextFloat() * 6.2832f
            val speed = (500f + Random.nextFloat() * 1300f) * power
            add(
                Particle(
                    layout = null, kind = KIND_SPARK, color = color,
                    x = at.x, y = at.y, vx = cos(a) * speed, vy = sin(a) * speed,
                    gravity = 900f, rot = 0f, vrot = 0f,
                    scale = 1f, life = 0.35f + Random.nextFloat() * 0.35f,
                )
            )
        }
    }

    fun confetti(at: Offset, count: Int) {
        repeat(count) {
            val a = -1.5708f + (Random.nextFloat() - 0.5f) * 2.4f
            val speed = 700f + Random.nextFloat() * 1100f
            add(
                Particle(
                    layout = null, kind = KIND_CONFETTI, color = CONFETTI_COLORS.random(),
                    x = at.x, y = at.y, vx = cos(a) * speed, vy = sin(a) * speed,
                    gravity = 1300f,
                    rot = Random.nextFloat() * 360f, vrot = Random.nextFloat() * 1080f - 540f,
                    scale = 0.8f + Random.nextFloat() * 0.8f,
                    life = 1.8f + Random.nextFloat(),
                    wobble = 4f + Random.nextFloat() * 4f,
                )
            )
        }
    }

    fun ring(at: Offset, color: Color, maxR: Float, life: Float = 0.5f, width: Float = 14f) {
        if (rings.size > 60) rings.removeAt(0)
        rings += Ring(at.x, at.y, color, maxR, life, width)
    }

    fun kick(shakeAmount: Float, flashAmount: Float = 0f, color: Color = Color.White) {
        shake = maxOf(shake, shakeAmount)
        if (flashAmount >= flash) flashColor = color
        flash = maxOf(flash, flashAmount)
    }

    fun step(dt: Float) {
        val it = particles.iterator()
        while (it.hasNext()) {
            val p = it.next()
            p.age += dt
            if (p.age >= p.life) {
                it.remove()
                continue
            }
            p.vy += p.gravity * dt
            if (p.kind == KIND_CONFETTI) {
                p.vx *= 1f - 1.5f * dt
                p.vy *= 1f - 1.5f * dt
            }
            p.x += (p.vx + if (p.wobble != 0f) sin(p.age * p.wobble) * 120f else 0f) * dt
            p.y += p.vy * dt
            p.rot += p.vrot * dt
        }
        val r = rings.iterator()
        while (r.hasNext()) {
            val ring = r.next()
            ring.age += dt
            if (ring.age >= ring.life) r.remove()
        }
        shake = (shake - dt * 60f).coerceAtLeast(0f)
        flash = (flash - dt * 2.5f).coerceAtLeast(0f)
        frame++
    }

    fun draw(scope: DrawScope) = with(scope) {
        for (ring in rings) {
            val t = ring.age / ring.life
            val ease = 1f - (1f - t) * (1f - t)
            drawCircle(
                ring.color.copy(alpha = (1f - t) * 0.8f),
                radius = ring.maxR * ease,
                center = Offset(ring.x, ring.y),
                style = Stroke(width = ring.width * (1f - t) + 1f),
            )
        }
        for (p in particles) {
            val t = p.age / p.life
            val alpha = ((if (t < 0.7f) 1f else (1f - t) / 0.3f) * p.alphaMul).coerceIn(0f, 1f)
            val center = Offset(p.x, p.y)
            when (p.kind) {
                KIND_SPARK -> drawLine(
                    p.color.copy(alpha = alpha),
                    start = center,
                    end = Offset(p.x - p.vx * 0.035f, p.y - p.vy * 0.035f),
                    strokeWidth = 5f,
                    cap = StrokeCap.Round,
                )
                KIND_CONFETTI -> rotate(p.rot, center) {
                    val w = 18f * p.scale
                    val h = 10f * p.scale * (0.3f + 0.7f * kotlin.math.abs(sin(p.age * 9f)))
                    drawRect(p.color.copy(alpha = alpha), Offset(p.x - w / 2, p.y - h / 2), Size(w, h))
                }
                else -> {
                    val layout = p.layout ?: continue
                    val w = layout.size.width.toFloat()
                    val h = layout.size.height.toFloat()
                    rotate(p.rot, center) {
                        scale(p.scale, center) {
                            drawText(layout, topLeft = Offset(p.x - w / 2, p.y - h / 2), alpha = alpha)
                        }
                    }
                }
            }
        }
    }
}
