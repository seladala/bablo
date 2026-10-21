package com.bablo.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
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

private class Particle(
    val layout: TextLayoutResult,
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
) {
    var age = 0f
}

/** Простой движок частиц: эмодзи-бабло, пузыри турбо-выбулькивателя, всплывающие цифры. */
class Fx(private val measurer: TextMeasurer) {
    private val particles = ArrayList<Particle>(512)
    private val emojiCache = HashMap<String, TextLayoutResult>()

    /** Читается в draw-лямбде, чтобы Canvas перерисовывался каждый кадр. */
    var frame by mutableLongStateOf(0L)
        private set

    var shake = 0f
        private set
    var flash = 0f
        private set

    private fun emoji(s: String) = emojiCache.getOrPut(s) {
        measurer.measure(s, TextStyle(fontSize = 34.sp))
    }

    private fun add(p: Particle) {
        if (particles.size > 600) particles.removeAt(0)
        particles += p
    }

    fun burst(at: Offset, emojis: List<String>, count: Int, power: Float = 1f) {
        repeat(count) {
            val a = Random.nextFloat() * 6.2832f
            val speed = (600f + Random.nextFloat() * 900f) * power
            add(
                Particle(
                    layout = emoji(emojis.random()),
                    x = at.x, y = at.y,
                    vx = cos(a) * speed,
                    vy = sin(a) * speed - 500f,
                    gravity = 2200f,
                    rot = Random.nextFloat() * 360f,
                    vrot = Random.nextFloat() * 720f - 360f,
                    scale = 0.6f + Random.nextFloat() * 0.8f,
                    life = 1.1f + Random.nextFloat() * 0.6f,
                )
            )
        }
    }

    fun floatingText(at: Offset, text: String, color: Color, big: Boolean = false) {
        val layout = measurer.measure(
            text,
            TextStyle(
                fontSize = if (big) 40.sp else 26.sp,
                fontWeight = FontWeight.Black,
                color = color,
            )
        )
        add(
            Particle(
                layout = layout,
                x = at.x + Random.nextFloat() * 160f - 80f, y = at.y - 60f,
                vx = Random.nextFloat() * 80f - 40f, vy = -520f,
                gravity = 300f, rot = 0f, vrot = 0f,
                scale = 1f, life = 1.2f,
            )
        )
    }

    fun bubble(at: Offset, radius: Float) {
        val a = Random.nextFloat() * 6.2832f
        add(
            Particle(
                layout = emoji(if (Random.nextInt(5) == 0) "💧" else "🫧"),
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
                layout = emoji(emojis.random()),
                x = Random.nextFloat() * width, y = -80f,
                vx = Random.nextFloat() * 100f - 50f, vy = 300f + Random.nextFloat() * 500f,
                gravity = 400f,
                rot = Random.nextFloat() * 360f, vrot = Random.nextFloat() * 300f - 150f,
                scale = 0.5f + Random.nextFloat() * 0.6f,
                life = 4f,
            )
        )
    }

    fun kick(shakeAmount: Float, flashAmount: Float = 0f) {
        shake = maxOf(shake, shakeAmount)
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
            p.x += (p.vx + if (p.wobble != 0f) sin(p.age * p.wobble) * 120f else 0f) * dt
            p.y += p.vy * dt
            p.rot += p.vrot * dt
        }
        shake = (shake - dt * 60f).coerceAtLeast(0f)
        flash = (flash - dt * 2.5f).coerceAtLeast(0f)
        frame++
    }

    fun draw(scope: DrawScope) = with(scope) {
        for (p in particles) {
            val t = p.age / p.life
            val alpha = if (t < 0.7f) 1f else (1f - t) / 0.3f
            val w = p.layout.size.width.toFloat()
            val h = p.layout.size.height.toFloat()
            val center = Offset(p.x, p.y)
            rotate(p.rot, center) {
                scale(p.scale, center) {
                    drawText(p.layout, topLeft = Offset(p.x - w / 2, p.y - h / 2), alpha = alpha.coerceIn(0f, 1f))
                }
            }
        }
    }
}
