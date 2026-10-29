package com.bablo.app

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sin
import kotlin.random.Random

enum class Sfx { COIN, CRIT, BUBBLE, BUY, DENIED, SIX_SEVEN, GOLDEN, FEVER, WITHDRAW, MODE, ACHIEVEMENT, HAMMAM, POP, ULTRA, ULTRA_SUPER, RING, DEP }

private const val SR = 22050
private const val TWO_PI = (2 * PI).toFloat()

/** Все звуки синтезируются на лету и кешируются в WAV, никаких аудиофайлов в APK. */
class SoundEngine(context: Context) {
    private val pool = SoundPool.Builder()
        .setMaxStreams(16)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        )
        .build()
    private val ids = IntArray(Sfx.entries.size)
    private val lastPlayed = LongArray(Sfx.entries.size)

    var enabled = true

    init {
        val dir = File(context.cacheDir, "sfx").apply { mkdirs() }
        for (s in Sfx.entries) {
            val f = File(dir, "${s.name.lowercase()}_v1.wav")
            if (!f.exists()) writeWav(f, synth(s))
            ids[s.ordinal] = pool.load(f.path, 1)
        }
    }

    fun play(s: Sfx, rate: Float = 1f, volume: Float = 0.8f) {
        if (!enabled) return
        val now = System.currentTimeMillis()
        if (now - lastPlayed[s.ordinal] < 35) return
        lastPlayed[s.ordinal] = now
        pool.play(ids[s.ordinal], volume, volume, 1, 0, rate.coerceIn(0.5f, 2f))
    }

    fun release() = pool.release()
}

private fun gen(sec: Float, f: (Float) -> Float) = FloatArray((sec * SR).toInt()) { i -> f(i / SR.toFloat()) }

private fun sine(freq: Float, t: Float) = sin(TWO_PI * freq * t)

private fun square(freq: Float, t: Float) = if (sine(freq, t) >= 0f) 1f else -1f

/** Нота с атакой и экспоненциальным затуханием; 0 вне [start, start+len]. */
private fun note(t: Float, start: Float, len: Float, freq: Float, decay: Float = 6f, wave: (Float, Float) -> Float = ::sine): Float {
    val lt = t - start
    if (lt < 0f || lt > len) return 0f
    val attack = (lt / 0.005f).coerceAtMost(1f)
    return wave(freq, lt) * attack * exp(-lt * decay)
}

private fun synth(s: Sfx): FloatArray {
    val rnd = Random(67)
    return when (s) {
        Sfx.COIN -> gen(0.3f) { t ->
            note(t, 0f, 0.07f, 1318.5f, 8f) * 0.6f + note(t, 0.06f, 0.24f, 1760f, 12f) * 0.6f +
                note(t, 0.06f, 0.24f, 3520f, 20f) * 0.15f
        }
        Sfx.CRIT -> gen(0.55f) { t ->
            val boom = sin(TWO_PI * (110f - 120f * t) * t) * exp(-t * 6f)
            val noise = (rnd.nextFloat() * 2f - 1f) * exp(-t * 22f) * 0.6f
            val chime = note(t, 0.02f, 0.5f, 2093f, 7f) * 0.3f
            boom * 0.8f + noise + chime
        }
        Sfx.BUBBLE -> gen(0.13f) { t ->
            sin(TWO_PI * (280f * t + 2800f * t * t)) * sin(PI.toFloat() * t / 0.13f) * 0.7f
        }
        Sfx.BUY -> gen(0.4f) { t ->
            note(t, 0f, 0.08f, 523.3f, 10f) + note(t, 0.07f, 0.08f, 659.3f, 10f) +
                note(t, 0.14f, 0.25f, 784f, 7f) + note(t, 0.14f, 0.25f, 1568f, 9f) * 0.3f
        }
        Sfx.DENIED -> gen(0.4f) { t ->
            val gap = if (t in 0.17f..0.2f) 0f else 1f
            square(if (t < 0.18f) 160f else 120f, t) * 0.3f * gap * (1f - t / 0.4f)
        }
        Sfx.SIX_SEVEN -> gen(0.55f) { t ->
            val vib = 1f + 0.02f * sin(TWO_PI * 7f * t)
            note(t, 0f, 0.22f, 392f * vib, 3f, ::square) * 0.25f + note(t, 0f, 0.22f, 784f * vib, 4f) * 0.3f +
                note(t, 0.26f, 0.29f, 523.3f * vib, 3f, ::square) * 0.25f + note(t, 0.26f, 0.29f, 1046.5f * vib, 4f) * 0.3f
        }
        Sfx.GOLDEN -> gen(0.7f) { t ->
            val notes = floatArrayOf(1046.5f, 1174.7f, 1318.5f, 1568f, 1760f, 2093f, 2349f, 2637f)
            var v = 0f
            for (i in notes.indices) v += note(t, i * 0.05f, 0.4f, notes[i], 8f) * 0.25f
            v
        }
        Sfx.FEVER -> gen(0.9f) { t ->
            val f = 200f + 1600f * t * t
            val trem = 0.6f + 0.4f * sin(TWO_PI * 18f * t)
            (sin(TWO_PI * (200f * t + 1600f / 3f * t * t * t)) * 0.4f + square(f / 2f, t) * 0.12f) * trem *
                (if (t > 0.75f) (0.9f - t) / 0.15f else 1f)
        }
        Sfx.WITHDRAW -> gen(1.2f) { t ->
            val ka = (rnd.nextFloat() * 2f - 1f) * (if (t < 0.06f) 0.7f else 0f) * exp(-t * 30f)
            ka + note(t, 0.07f, 1.1f, 2637f, 3f) * 0.35f + note(t, 0.07f, 1.1f, 3520f, 4f) * 0.25f +
                note(t, 0.07f, 1.1f, 1318.5f, 3f) * 0.3f
        }
        Sfx.MODE -> gen(0.35f) { t ->
            sin(TWO_PI * (400f * t + 1200f * t * t)) * 0.5f * (1f - t / 0.35f)
        }
        Sfx.ACHIEVEMENT -> gen(0.6f) { t ->
            note(t, 0f, 0.1f, 523.3f, 8f) * 0.4f + note(t, 0.09f, 0.1f, 659.3f, 8f) * 0.4f +
                note(t, 0.18f, 0.1f, 784f, 8f) * 0.4f + note(t, 0.27f, 0.33f, 1046.5f, 5f) * 0.5f
        }
        Sfx.POP -> gen(0.09f) { t ->
            // лопнувший пузырь: короткий щелчок + писк вверх
            val click = (rnd.nextFloat() * 2f - 1f) * exp(-t * 120f) * 0.6f
            click + sin(TWO_PI * (600f * t + 9000f * t * t)) * exp(-t * 35f) * 0.5f
        }
        Sfx.ULTRA -> gen(1.0f) { t ->
            // нарастающий аккорд мажор + удар
            val boom = sin(TWO_PI * (90f - 60f * t) * t) * exp(-t * 5f) * 0.6f
            boom + note(t, 0f, 1f, 523.3f, 2.5f) * 0.25f + note(t, 0.08f, 0.92f, 659.3f, 2.5f) * 0.25f +
                note(t, 0.16f, 0.84f, 784f, 2.5f) * 0.25f + note(t, 0.24f, 0.76f, 1046.5f, 2.5f, ::square) * 0.12f
        }
        Sfx.ULTRA_SUPER -> gen(1.6f) { t ->
            // сирена-свип + арпеджио 6-7 по кругу
            val sweep = sin(TWO_PI * (300f * t + 700f * t * t)) * 0.25f * (0.6f + 0.4f * sin(TWO_PI * 12f * t))
            val step = (t / 0.1f).toInt()
            val f = if (step % 2 == 0) 783.99f * (1f + 0.06f * (step / 2)) else 1046.5f * (1f + 0.06f * (step / 2))
            val arp = square(f, t) * 0.12f * exp(-(t - step * 0.1f) * 12f)
            val boom = sin(TWO_PI * 60f * t) * exp(-t * 3f) * 0.5f
            (sweep + arp + boom) * (if (t > 1.4f) (1.6f - t) / 0.2f else 1f)
        }
        Sfx.RING -> gen(1.2f) { t ->
            // старый добрый телефон: две трели 440+480 Гц с дребезгом звонка
            val on = (t % 0.6f) < 0.4f
            val trill = if (sin(TWO_PI * 20f * t) > 0f) 1f else 0.4f
            if (on) (sine(440f, t) + sine(480f, t)) * 0.3f * trill else 0f
        }
        Sfx.DEP -> gen(0.8f) { t ->
            // барабан слотов: тики с ускорением + звонок в конце
            val tick = note(t % 0.08f, 0f, 0.03f, 1800f, 60f, ::square) * 0.2f * (if (t < 0.55f) 1f else 0f)
            tick + note(t, 0.55f, 0.25f, 1568f, 8f) * 0.4f + note(t, 0.6f, 0.2f, 2093f, 9f) * 0.3f
        }
        Sfx.HAMMAM -> gen(0.9f) { t ->
            // шипение пара + низкий гонг
            val steam = (rnd.nextFloat() * 2f - 1f) * 0.25f * sin(PI.toFloat() * t / 0.9f)
            steam + note(t, 0f, 0.9f, 110f, 3f) * 0.5f + note(t, 0f, 0.9f, 220f, 4f) * 0.25f
        }
    }
}

private fun writeWav(file: File, samples: FloatArray) {
    val data = ByteBuffer.allocate(samples.size * 2).order(ByteOrder.LITTLE_ENDIAN)
    for (v in samples) data.putShort((v.coerceIn(-1f, 1f) * 32000f).toInt().toShort())
    val header = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN).apply {
        put("RIFF".toByteArray())
        putInt(36 + samples.size * 2)
        put("WAVE".toByteArray())
        put("fmt ".toByteArray())
        putInt(16)
        putShort(1)
        putShort(1)
        putInt(SR)
        putInt(SR * 2)
        putShort(2)
        putShort(16)
        put("data".toByteArray())
        putInt(samples.size * 2)
    }
    FileOutputStream(file).use {
        it.write(header.array())
        it.write(data.array())
    }
}
