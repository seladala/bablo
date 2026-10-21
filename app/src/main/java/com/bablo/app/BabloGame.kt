package com.bablo.app

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlin.math.floor
import kotlin.math.log10
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
    AUTO("🤖", "Автобабломёт", "+1 тык в секунду сам по себе", 50.0, 1.5, 999),
    MULT("💎", "Бабломножитель", "x2 ко всему баблу за уровень", 1_000.0, 2.6, 200),
    CRIT("⚡", "Критобабло", "+5% шанс крита (x10 бабла)", 250.0, 1.9, 10),
    COMBO("🔥", "Комбо-усилитель", "Комбо даёт больше бонуса", 500.0, 2.2, 20),
    SIZE("🟢", "Ультимативный размер", "Кнопка больше и мощнее (+67%)", 300.0, 2.67, 6),
    HOLD("✊", "Турбо-зажим", "Быстрее автотыки при зажатии кнопки", 400.0, 2.0, 15),
    TURBO_TIME("⏱️", "Долгий буль", "+5 сек работы турбо-выбулькивателя", 800.0, 2.5, 12),
    LUCK("🍀", "Бабло-удача", "Золотые мешки чаще и щедрее", 2_000.0, 2.8, 10),
}

enum class Skin(val title: String, val emoji: String, val price: Double, val colors: List<Color>, val bonus: Double) {
    CLASSIC("Классика", "🟢", 0.0, listOf(Color(0xFFB9F6CA), Color(0xFF00E676), Color(0xFF1B5E20)), 1.0),
    GOLD("Золото", "🟡", 1e6, listOf(Color(0xFFFFF9C4), Color(0xFFFFD600), Color(0xFFFF6F00)), 1.1),
    NEON("Неон", "🟣", 1e9, listOf(Color(0xFFF8BBD0), Color(0xFFFF4081), Color(0xFF4A148C)), 1.2),
    ICE("Лёд", "🔵", 1e12, listOf(Color(0xFFE1F5FE), Color(0xFF40C4FF), Color(0xFF01579B)), 1.3),
    LAVA("Лава", "🔴", 1e15, listOf(Color(0xFFFFF3E0), Color(0xFFFF3D00), Color(0xFF3E0000)), 1.5),
    RAINBOW("Радуга", "🌈", 1e18, listOf(Color.White, Color.Magenta, Color.Blue), 1.67),
    HAMMAM("Хаммам", "🧖", 1e21, listOf(Color(0xFFFFFFFF), Color(0xFFFFCC80), Color(0xFF6D4C41)), 2.0),
    ULTRA67("ULTRA 6-7", "6️⃣7️⃣", 6.7e25, listOf(Color(0xFFFFE57F), Color(0xFFAA00FF), Color(0xFF12005E)), 6.7),
}

enum class Achievement(val emoji: String, val title: String, val description: String, val check: (BabloGame) -> Boolean) {
    FIRST("👆", "Первый тык", "Нажать на БАБЛО", { it.totalClicks >= 1 }),
    CLICKS_67("6️⃣", "Шестьдесят семь", "67 тыков", { it.totalClicks >= 67 }),
    CLICKS_6767("7️⃣", "6767", "6767 тыков", { it.totalClicks >= 6767 }),
    THOUSAND("💵", "Первая тыща", "1 000 бабла", { it.totalEarned >= 1e3 }),
    MILLION("💰", "Миллионер", "1 млн бабла", { it.totalEarned >= 1e6 }),
    BILLION("🏦", "Миллиардер", "1 млрд бабла", { it.totalEarned >= 1e9 }),
    QUINT("🪐", "Квинтиллионер", "10^18 бабла", { it.totalEarned >= 1e18 }),
    COMBO_25("🔥", "Горячие пальцы", "Комбо x25", { it.maxCombo >= 25 }),
    COMBO_67("💥", "ULTRA 6-7", "Комбо x67 — включить лихорадку", { it.maxCombo >= 67 }),
    HAMMAM_1("🚫", "Наивный", "Попытаться купить хаммам", { it.hammamAttempts >= 1 }),
    HAMMAM_67("🧱", "Упрямый", "67 раз попытаться купить хаммам", { it.hammamAttempts >= 67 }),
    HAMMAM_UP("🧖", "Пар пошёл", "Улучшить хаммам (не покупая!)", { it.hammamLevel >= 1 }),
    HAMMAM_MAX("👑", "Хозяин пара", "Хаммам на максимум", { it.hammamLevel >= HAMMAM_STAGES.size }),
    GOLDEN("✨", "Золотая рука", "Поймать золотой мешок", { it.goldenCaught >= 1 }),
    GOLDEN_10("🌟", "Ловец мешков", "Поймать 10 золотых мешков", { it.goldenCaught >= 10 }),
    SKIN("🎨", "Модник", "Купить скин кнопки", { it.ownedSkins.size >= 2 }),
    WITHDRAW("💳", "Вывел!", "Вывести 68 квинтиллиардов", { it.withdrawals >= 1 }),
}

val HAMMAM_STAGES = listOf(
    "Холодный предбанник", "Тёплый пар", "Берёзовый веник", "Мраморный лежак", "Турецкий массаж",
    "Золотой таз", "Пар 67°", "Хрустальный купол", "Королевская пена", "Ультра-пар",
    "Хаммам 6-7", "Космический хаммам", "Хаммам вечности", "Хаммам абсолюта", "ULTRA 6-7 ХАММАМ",
)

data class GoldenBag(val xFrac: Float, val yFrac: Float, val expiresAt: Long)

/** Всё состояние игры. Хаммам купить нельзя — но его можно улучшать. Это философия. */
class BabloGame(context: Context) {
    private val prefs = context.getSharedPreferences("bablo", Context.MODE_PRIVATE)

    var money by mutableDoubleStateOf(Double.fromBits(prefs.getLong("money", 0L)))
        private set
    var totalEarned by mutableDoubleStateOf(Double.fromBits(prefs.getLong("earned", 0L)))
        private set
    var totalClicks by mutableIntStateOf(prefs.getInt("clicks", 0))
        private set
    var maxCombo by mutableIntStateOf(prefs.getInt("maxCombo", 0))
        private set
    var combo by mutableIntStateOf(0)
        private set
    var hammamAttempts by mutableIntStateOf(prefs.getInt("hammam", 0))
        private set
    var hammamLevel by mutableIntStateOf(prefs.getInt("hammamLevel", 0))
        private set
    var tokens by mutableLongStateOf(prefs.getLong("tokens", 0L))
        private set
    var withdrawals by mutableIntStateOf(prefs.getInt("withdrawals", 0))
        private set
    var goldenCaught by mutableIntStateOf(prefs.getInt("golden", 0))
        private set

    var ultraBablo by mutableStateOf(prefs.getBoolean("ultra", false))
    var soundOn by mutableStateOf(prefs.getBoolean("sound", true))
    var vibrationOn by mutableStateOf(prefs.getBoolean("vibration", true))

    var skin by mutableStateOf(Skin.entries.firstOrNull { it.name == prefs.getString("skin", null) } ?: Skin.CLASSIC)
    val ownedSkins = mutableStateListOf<Skin>().apply {
        add(Skin.CLASSIC)
        prefs.getStringSet("skins", emptySet())!!.forEach { n ->
            Skin.entries.firstOrNull { it.name == n && it != Skin.CLASSIC }?.let { add(it) }
        }
    }

    val unlocked = mutableStateListOf<Achievement>().apply {
        prefs.getStringSet("achievements", emptySet())!!.forEach { n ->
            Achievement.entries.firstOrNull { it.name == n }?.let { add(it) }
        }
    }
    /** Только что полученные достижения, UI показывает и убирает их. */
    val pendingAchievements = mutableStateListOf<Achievement>()

    /** Часы игры: обновляются ~10 раз в секунду, от них зависят все таймеры. */
    var clock by mutableLongStateOf(System.currentTimeMillis())
        private set

    private var turboEnd by mutableLongStateOf(prefs.getLong("turboEnd", 0L))
    private var turboReady by mutableLongStateOf(prefs.getLong("turboReady", 0L))
    private var m67End by mutableLongStateOf(prefs.getLong("m67End", 0L))
    private var m67Ready by mutableLongStateOf(prefs.getLong("m67Ready", 0L))
    private var feverEnd by mutableLongStateOf(0L)
    private var frenzyEnd by mutableLongStateOf(0L)

    var golden by mutableStateOf<GoldenBag?>(null)
        private set
    private var nextGoldenAt = System.currentTimeMillis() + 20_000

    private val levels = Upgrade.entries.associateWith {
        mutableIntStateOf(prefs.getInt("lvl_${it.name}", 0))
    }

    private var lastClickMs = 0L
    private var autoAcc = 0.0
    private var turboAcc = 0.0
    private var achievementAcc = 0.0

    // ---------- Улучшения ----------

    fun level(u: Upgrade): Int = levels.getValue(u).intValue

    fun cost(u: Upgrade): Double = u.baseCost * u.growth.pow(level(u))

    fun canBuy(u: Upgrade) = level(u) < u.maxLevel && money >= cost(u)

    fun buy(u: Upgrade): Boolean {
        if (!canBuy(u)) return false
        money -= cost(u)
        levels.getValue(u).intValue++
        return true
    }

    // ---------- Хаммам ----------

    /** Попытка купить хаммам. Всегда неудачная. */
    fun tryBuyHammam(): String {
        hammamAttempts++
        return HAMMAM_REFUSALS[(hammamAttempts - 1) % HAMMAM_REFUSALS.size]
    }

    val hammamMaxed get() = hammamLevel >= HAMMAM_STAGES.size
    val hammamCost: Double get() = 6_700.0 * 14.0.pow(hammamLevel)
    fun canUpgradeHammam() = !hammamMaxed && money >= hammamCost

    fun upgradeHammam(): Boolean {
        if (!canUpgradeHammam()) return false
        money -= hammamCost
        hammamLevel++
        return true
    }

    /** Расслабон из хаммама сокращает перезарядку режимов. */
    val cooldownFactor: Double get() = (1.0 - 0.04 * hammamLevel).coerceAtLeast(0.4)

    // ---------- Скины ----------

    fun skinLockReason(s: Skin): String? = when {
        s == Skin.HAMMAM && hammamLevel < 5 -> "нужен хаммам ур. 5"
        s == Skin.ULTRA67 && withdrawals < 1 -> "нужен 1 вывод"
        else -> null
    }

    fun buySkin(s: Skin): Boolean {
        if (s in ownedSkins || skinLockReason(s) != null || money < s.price) return false
        money -= s.price
        ownedSkins += s
        skin = s
        return true
    }

    // ---------- Режимы с таймерами ----------

    val turbo: Boolean get() = clock < turboEnd
    val turboDurationMs: Long get() = 20_000L + 5_000L * level(Upgrade.TURBO_TIME)
    val turboCooldownMs: Long get() = (60_000 * cooldownFactor).toLong()
    val turboLeftMs: Long get() = (turboEnd - clock).coerceAtLeast(0)
    val turboReadyInMs: Long get() = (turboReady - clock).coerceAtLeast(0)

    fun activateTurbo(): Boolean {
        val now = System.currentTimeMillis()
        if (now < turboReady) return false
        turboEnd = now + turboDurationMs
        turboReady = turboEnd + turboCooldownMs
        clock = now
        return true
    }

    val mode67: Boolean get() = clock < m67End
    val mode67LeftMs: Long get() = (m67End - clock).coerceAtLeast(0)
    val mode67ReadyInMs: Long get() = (m67Ready - clock).coerceAtLeast(0)
    val mode67CooldownMs: Long get() = (167_000 * cooldownFactor).toLong()

    fun activate67(): Boolean {
        val now = System.currentTimeMillis()
        if (now < m67Ready) return false
        m67End = now + MODE67_DURATION
        m67Ready = m67End + mode67CooldownMs
        clock = now
        return true
    }

    /** ULTRA 6-7 лихорадка: включается на каждом комбо, кратном 67. */
    val fever: Boolean get() = clock < feverEnd
    val feverLeftMs: Long get() = (feverEnd - clock).coerceAtLeast(0)

    /** Бабло-безумие от золотого мешка. */
    val frenzy: Boolean get() = clock < frenzyEnd

    // ---------- Экономика ----------

    val multiplier: Double
        get() {
            var m = 2.0.pow(level(Upgrade.MULT)) * 2.0.pow(hammamLevel) * (1.0 + 0.67 * tokens) * skin.bonus
            if (mode67) m *= 67.0
            if (ultraBablo) m *= 10.0
            if (turbo) m *= 1.5
            if (fever) m *= 67.0
            if (frenzy) m *= 7.0
            return m
        }

    val critChance: Float get() = 0.05f * level(Upgrade.CRIT)

    val comboBonus: Double get() = combo * (0.01 + 0.01 * level(Upgrade.COMBO))

    val perClick: Double get() = (1.0 + level(Upgrade.POWER)) * (1.0 + level(Upgrade.SIZE) * 0.67) * multiplier

    val perSecond: Double get() = level(Upgrade.AUTO) * perClick * 0.5

    val holdRate: Double get() = 8.0 + 2.0 * level(Upgrade.HOLD)

    val buttonScale: Float get() = 1f + level(Upgrade.SIZE) * 0.06f

    private fun earn(x: Double) {
        money += x
        totalEarned += x
    }

    data class ClickResult(val gain: Double, val crit: Boolean, val combo: Int, val fever: Boolean)

    fun click(auto: Boolean = false): ClickResult {
        val now = System.currentTimeMillis()
        var feverStarted = false
        if (!auto) {
            combo = if (now - lastClickMs < 450) (combo + 1).coerceAtMost(6767) else 1
            lastClickMs = now
            totalClicks++
            if (combo > maxCombo) maxCombo = combo
            if (combo % 67 == 0) {
                feverEnd = now + FEVER_DURATION
                clock = now
                feverStarted = true
            }
        }
        val crit = Random.nextFloat() < critChance
        var gain = perClick * (1.0 + comboBonus)
        if (crit) gain *= 10.0
        if (auto) gain *= 0.5
        earn(gain)
        return ClickResult(gain, crit, combo, feverStarted)
    }

    // ---------- Золотой мешок ----------

    data class GoldenReward(val text: String, val amount: Double)

    fun catchGolden(): GoldenReward? {
        golden ?: return null
        golden = null
        goldenCaught++
        scheduleGolden()
        val luck = 1.0 + 0.5 * level(Upgrade.LUCK)
        return if (Random.nextBoolean()) {
            frenzyEnd = System.currentTimeMillis() + (15_000 * luck).toLong()
            GoldenReward("БАБЛО-БЕЗУМИЕ x7!", 0.0)
        } else {
            val amount = (perSecond * 67 + perClick * 67) * luck
            earn(amount)
            GoldenReward("БАБЛО-ФОНТАН +${formatBablo(amount)}", amount)
        }
    }

    private fun scheduleGolden() {
        val base = 40_000 + Random.nextLong(60_000)
        nextGoldenAt = System.currentTimeMillis() + (base / (1.0 + 0.25 * level(Upgrade.LUCK))).toLong()
    }

    // ---------- Вывод (престиж) ----------

    val canWithdraw get() = money >= WITHDRAW_MIN
    val withdrawProgress: Float get() = (money / WITHDRAW_MIN).toFloat().coerceIn(0f, 1f)
    val tokensForWithdraw: Long get() = if (!canWithdraw) 0 else 1 + floor(log10(money / WITHDRAW_MIN)).toLong()

    /** Выводит всё бабло, сбрасывает улучшения кнопки и выдаёт жетоны 67. Хаммам остаётся. */
    fun withdraw(): Long {
        if (!canWithdraw) return 0
        val gained = tokensForWithdraw
        tokens += gained
        withdrawals++
        money = 0.0
        levels.values.forEach { it.intValue = 0 }
        return gained
    }

    // ---------- Тик ----------

    /** Возвращает количество турбо-бульков, которые надо отрисовать за этот кадр. */
    fun tick(dtSec: Double): Int {
        val now = System.currentTimeMillis()
        if (now - clock >= 100) clock = now
        if (now - lastClickMs > 900 && combo > 0) combo = 0

        autoAcc += perSecond * dtSec
        if (autoAcc >= 1.0) {
            earn(autoAcc)
            autoAcc = 0.0
        }

        val g = golden
        if (g == null && now >= nextGoldenAt) {
            golden = GoldenBag(0.1f + Random.nextFloat() * 0.8f, 0.25f + Random.nextFloat() * 0.5f, now + 9_000)
        } else if (g != null && now > g.expiresAt) {
            golden = null
            scheduleGolden()
        }

        achievementAcc += dtSec
        if (achievementAcc > 0.5) {
            achievementAcc = 0.0
            Achievement.entries.forEach {
                if (it !in unlocked && it.check(this)) {
                    unlocked += it
                    pendingAchievements += it
                }
            }
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
            putLong("earned", totalEarned.toRawBits())
            putInt("clicks", totalClicks)
            putInt("maxCombo", maxCombo)
            putInt("hammam", hammamAttempts)
            putInt("hammamLevel", hammamLevel)
            putLong("tokens", tokens)
            putInt("withdrawals", withdrawals)
            putInt("golden", goldenCaught)
            putBoolean("ultra", ultraBablo)
            putBoolean("sound", soundOn)
            putBoolean("vibration", vibrationOn)
            putString("skin", skin.name)
            putStringSet("skins", ownedSkins.map { it.name }.toSet())
            putStringSet("achievements", unlocked.map { it.name }.toSet())
            putLong("turboEnd", turboEnd)
            putLong("turboReady", turboReady)
            putLong("m67End", m67End)
            putLong("m67Ready", m67Ready)
            levels.forEach { (u, s) -> putInt("lvl_${u.name}", s.intValue) }
        }.apply()
    }

    companion object {
        const val TURBO_RATE = 12.0
        const val MODE67_DURATION = 67_000L
        const val FEVER_DURATION = 6_700L

        /** 68 квинтиллиардов = 68 × 10^33. */
        const val WITHDRAW_MIN = 6.8e34

        val HAMMAM_REFUSALS = listOf(
            "Хаммам нельзя купить. Хаммам можно только заслужить.",
            "Бабла много, а пара — нет. Хаммам не продаётся.",
            "Брат, хаммам не для продажи 🤝",
            "Ошибка 67: хаммам слишком ультимативен.",
            "Даже ультра-бабло не открывает двери хаммама.",
            "Турбо-выбулькиватель тоже пытался. Не вышло.",
            "Хаммам смотрит на тебя. Хаммам говорит «нет».",
            "Купить нельзя. Улучшить — можно. Чувствуешь разницу?",
        )
    }
}

private val UNITS = listOf(
    "К", "М", "Млрд", "Трлн", "Квдрлн", "Квнтлн", "Скстлн", "Сптлн", "Октлн", "Нонлн", "Квнтлрд",
)

fun formatBablo(v: Double): String {
    if (v < 1_000) return if (v < 10 && v % 1.0 != 0.0) "%.1f".format(v) else v.toLong().toString()
    var x = v
    var i = -1
    while (x >= 1_000 && i < UNITS.lastIndex) {
        x /= 1_000
        i++
    }
    if (x >= 1_000) return "%.2e".format(v)
    return (if (x < 100) "%.2f" else "%.0f").format(x) + " " + UNITS[i]
}

fun formatTime(ms: Long): String {
    val s = (ms + 999) / 1000
    return if (s >= 60) "%d:%02d".format(s / 60, s % 60) else "${s}с"
}
