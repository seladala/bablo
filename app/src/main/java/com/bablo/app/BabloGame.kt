package com.bablo.app

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.pow
import kotlin.random.Random

enum class Upgrade(
    val emoji: String,
    val title: String,
    val description: String,
    val baseCost: Double,
    val growth: Double,
    val maxLevel: Int,
) {
    POWER("👊", "Сила нажатия", "+1 бабло за каждый тык", 10.0, 1.45, 999),
    AUTO("🤖", "Автобабломёт", "+1 бабло в секунду за уровень", 50.0, 1.55, 999),
    CRIT("⚡", "Критобабло", "+5% шанс крита (x10 бабла)", 250.0, 1.9, 10),
    MULT("💎", "Бабломножитель", "x2, x3, x4… ко всему баблу", 1_000.0, 3.0, 50),
    COMBO("🔥", "Комбо-усилитель", "Комбо даёт больше бонуса", 500.0, 2.2, 20),
    SIZE("🟢", "Ультимативный размер", "Кнопка становится больше и мощнее", 300.0, 2.67, 6),
}

/** Всё состояние игры. Хаммам купить нельзя — это не баг, это философия. */
class BabloGame(context: Context) {
    private val prefs = context.getSharedPreferences("bablo", Context.MODE_PRIVATE)

    var money by mutableDoubleStateOf(Double.fromBits(prefs.getLong("money", 0L)))
        private set
    var totalClicks by mutableIntStateOf(prefs.getInt("clicks", 0))
        private set
    var combo by mutableIntStateOf(0)
        private set
    var hammamAttempts by mutableIntStateOf(prefs.getInt("hammam", 0))
        private set

    var mode67 by mutableStateOf(prefs.getBoolean("mode67", false))
    var ultraBablo by mutableStateOf(prefs.getBoolean("ultra", false))
    var turbo by mutableStateOf(prefs.getBoolean("turbo", false))

    private val levels = Upgrade.entries.associateWith {
        mutableIntStateOf(prefs.getInt("lvl_${it.name}", 0))
    }

    private var lastClickMs = 0L
    private var autoAcc = 0.0
    private var turboAcc = 0.0

    fun level(u: Upgrade): Int = levels.getValue(u).intValue

    fun cost(u: Upgrade): Double = u.baseCost * u.growth.pow(level(u))

    fun canBuy(u: Upgrade) = level(u) < u.maxLevel && money >= cost(u)

    fun buy(u: Upgrade): Boolean {
        if (!canBuy(u)) return false
        money -= cost(u)
        levels.getValue(u).intValue++
        return true
    }

    /** Попытка купить хаммам. Всегда неудачная. */
    fun tryBuyHammam(): String {
        hammamAttempts++
        return HAMMAM_REFUSALS[(hammamAttempts - 1) % HAMMAM_REFUSALS.size]
    }

    val multiplier: Double
        get() {
            var m = 1.0 + level(Upgrade.MULT)
            if (mode67) m *= 67.0
            if (ultraBablo) m *= 10.0
            if (turbo) m *= 1.5
            return m
        }

    val critChance: Float get() = 0.05f * level(Upgrade.CRIT)

    val comboBonus: Double get() = combo * (0.01 + 0.01 * level(Upgrade.COMBO))

    val perClick: Double get() = (1.0 + level(Upgrade.POWER)) * (1.0 + level(Upgrade.SIZE) * 0.67) * multiplier

    val perSecond: Double get() = level(Upgrade.AUTO) * multiplier

    val buttonScale: Float get() = 1f + level(Upgrade.SIZE) * 0.06f

    data class ClickResult(val gain: Double, val crit: Boolean, val combo: Int)

    fun click(auto: Boolean = false): ClickResult {
        val now = System.currentTimeMillis()
        if (!auto) {
            combo = if (now - lastClickMs < 450) (combo + 1).coerceAtMost(667) else 1
            lastClickMs = now
            totalClicks++
        }
        val crit = Random.nextFloat() < critChance
        var gain = perClick * (1.0 + comboBonus)
        if (crit) gain *= 10.0
        if (auto) gain *= 0.5
        money += gain
        return ClickResult(gain, crit, combo)
    }

    /** Возвращает количество турбо-бульков, которые надо отрисовать за этот кадр. */
    fun tick(dtSec: Double): Int {
        if (System.currentTimeMillis() - lastClickMs > 900 && combo > 0) combo = 0
        autoAcc += perSecond * dtSec
        if (autoAcc >= 1.0 || (autoAcc > 0 && dtSec > 0.5)) {
            money += autoAcc
            autoAcc = 0.0
        }
        if (!turbo) return 0
        turboAcc += TURBO_RATE * dtSec
        val bulks = turboAcc.toInt()
        turboAcc -= bulks
        return bulks
    }

    fun save() {
        prefs.edit().apply {
            putLong("money", money.toRawBits())
            putInt("clicks", totalClicks)
            putInt("hammam", hammamAttempts)
            putBoolean("mode67", mode67)
            putBoolean("ultra", ultraBablo)
            putBoolean("turbo", turbo)
            levels.forEach { (u, s) -> putInt("lvl_${u.name}", s.intValue) }
        }.apply()
    }

    companion object {
        const val TURBO_RATE = 12.0

        val HAMMAM_REFUSALS = listOf(
            "Хаммам нельзя купить. Хаммам можно только заслужить.",
            "Бабла много, а пара — нет. Хаммам не продаётся.",
            "Брат, хаммам не для продажи 🤝",
            "Ошибка 67: хаммам слишком ультимативен.",
            "Даже ультра-бабло не открывает двери хаммама.",
            "Турбо-выбулькиватель тоже пытался. Не вышло.",
            "Хаммам смотрит на тебя. Хаммам говорит «нет».",
        )
    }
}

fun formatBablo(v: Double): String {
    if (v < 1_000) return if (v < 10 && v % 1.0 != 0.0) "%.1f".format(v) else v.toLong().toString()
    val units = listOf("К", "М", "Млрд", "Трлн", "Квдр", "Квнт", "Скст", "Сптл")
    var x = v
    var i = -1
    while (x >= 1_000 && i < units.lastIndex) {
        x /= 1_000
        i++
    }
    if (x >= 1_000) return "%.2e".format(v)
    return (if (x < 100) "%.2f" else "%.0f").format(x) + " " + units[i]
}
