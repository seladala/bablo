package com.bablo.app

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlin.math.floor
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.pow
import kotlin.random.Random

/**
 * Генераторы бабла. Каждый следующий в 100 раз дороже и в 65 раз доходнее предыдущего,
 * так что окупаемость медленно растёт и каждые три нуля даются примерно за полтора часа игры.
 */
enum class Gen(val emoji: String, val title: String, val flavor: String) {
    GRANNY("👵", "Бабушкин кошелёк", "Бабушка подкидывает на мороженое"),
    SHAWARMA("🌯", "Шаурмичная", "Шаурма с баблом внутри"),
    ATM("🏧", "Банкомат-67", "Выдаёт только купюры по 67"),
    FARM("🌱", "Бабло-ферма", "Бабло растёт на деревьях"),
    MINER("⛏️", "Крипто-майнер", "Майнит БаблоКоин"),
    FACTORY("🏭", "Бабло-завод", "Печатает бабло в три смены"),
    OFFSHORE("🏝️", "Офшор", "Бабло загорает и множится"),
    CENTRAL("🏦", "Центробанк", "Сам решает, сколько бабла нужно"),
    OIL("🛢️", "Бабло-вышка", "Качает жидкое бабло из недр"),
    PORTAL("🌀", "Бабло-портал 67", "Бабло из параллельной вселенной"),
    SPACE("🚀", "Космобанк", "Филиалы на орбите"),
    GALAXY("🪐", "Галактический ЦБ", "Курс бабла к звёздам"),
    TIME("⏳", "Машина времени", "Привозит бабло из будущего"),
    DNA("🧬", "Бабло-ДНК", "Бабло в каждой клетке"),
    MULTIVERSE("🌌", "Мультивселенная", "Бабло во всех вселенных сразу"),
    REACTOR("⚛️", "ULTRA 6-7 Реактор", "Расщепляет 6 и 7 на бабло"),
    EYE("👁️", "Око Бабла", "Видит всё бабло мира"),
    HAMMAM_DIM("♨️", "Хаммам-измерение", "Измерение, где всё — пар и бабло");

    val baseCost: Double get() = 15.0 * 100.0.pow(ordinal)
    val baseOut: Double get() = 0.1 * 65.0.pow(ordinal)
}

val GEN_MILESTONES = intArrayOf(25, 50, 100, 200, 300, 400, 500)

/** Разовые улучшения кнопки. У каждого — своя механика, а не просто «+1». */
enum class Perk(
    val emoji: String,
    val title: String,
    val description: String,
    val cost: Double,
    val requires: String? = null,
) {
    SAUSAGE("🌭", "Пальцы-сосиски", "Тык x2", 150.0),
    TITAN("🦾", "Титановый палец", "Тык ещё x2", 5e3, "SAUSAGE"),
    CRIT("⚡", "Критический палец", "5% шанс крита: тык x10", 2e3),
    HOLD("✊", "Турбо-зажим", "Зажатая кнопка тычет вдвое быстрее", 2e4),
    PCT1("💹", "Тык с процентом", "Тык приносит +1% дохода/сек", 5e4),
    SIZE("🟢", "Ультимативный размер", "Кнопка больше, тык x2", 6.7e4),
    COMBO("🔥", "Комбо-мастер", "Комбо +2% за тык (вместо 1%) и прощает паузы", 1e5),
    ECHO("🔁", "Эхо-тык", "10% шанс, что тык отзовётся эхом x3", 3e5),
    LONG_BUL("🫧", "Долгий буль", "Турбо-выбулькиватель работает на 15 сек дольше и пускает больше пузырей", 1e6),
    SHARP("🔪", "Острый крит", "Крит x25 вместо x10", 2e6, "CRIT"),
    MAGNET("🧲", "Магнит мешков", "Золотые мешки чаще и живут дольше", 5e6),
    ACCU("🔋", "Ультра-аккумулятор", "Ультра-бабло включается от удержания вдвое быстрее", 1e7),
    PCT2("💹", "Тык с двумя процентами", "Ещё +2% дохода/сек к тыку", 5e7, "PCT1"),
    GOLD67("👑", "Золотая кнопка 67", "Тык x6.7", 6.7e7, "SIZE"),
    RESONANCE("🫧", "Буль-резонанс", "Каждый пойманный пузырь приносит ещё 3 сек дохода", 1e8, "LONG_BUL"),
    MANIA("🎯", "Крит-мания", "Шанс крита +7%", 5e8, "SHARP"),
    SHAWARMA_CRIT("🌯", "Шаурма-крит", "3% критов выбивают золотой мешок", 5e9, "MANIA"),
    SIXTH_SENSE("6️⃣", "Шестьдесят седьмое чувство", "Режим 67 длится вдвое дольше", 6.7e9),
    PCT3("💹", "Тык с тремя процентами", "Ещё +3% дохода/сек к тыку", 5e10, "PCT2"),
    ROULETTE("🎰", "Бабло-рулетка", "Каждый 67-й тык — случайный приз", 1e12),
    ULTRA_FINGER("💥", "ULTRA 6-7 палец", "Лихорадка ULTRA 6-7 даёт x67 вместо x6.7", 6.7e13, "GOLD67");

    val requiresPerk: Perk? get() = requires?.let { valueOf(it) }
}

data class HammamStage(val title: String, val perk: String)

/** Хаммам нельзя купить. Но каждый этап улучшения открывает новую механику. */
val HAMMAM_STAGES = listOf(
    HammamStage("Холодный предбанник", "Открывает жар: каждый тык греет хаммам, жар усиливает тык до x2"),
    HammamStage("Тёплый пар", "Хаммам остывает вдвое медленнее"),
    HammamStage("Берёзовый веник", "При жаре от 67° шанс крита +10%"),
    HammamStage("Мраморный лежак", "Жар усиливает и генераторы"),
    HammamStage("Турецкий массаж", "Золотые мешки появляются вдвое чаще"),
    HammamStage("Золотой таз", "Кипение: при жаре от 90° каждые 10 сек капает 10 сек дохода"),
    HammamStage("Пар 67°", "Перезарядка режима 67 на треть короче"),
    HammamStage("Хрустальный купол", "Максимальный жар 150°"),
    HammamStage("Королевская пена", "Комбо не сгорает 2 сек"),
    HammamStage("Ультра-пар", "Ультра-бабло и ультра супер бабло включаются вдвое быстрее"),
    HammamStage("Хаммам 6-7", "Лихорадка ULTRA 6-7 длится вдвое дольше"),
    HammamStage("Космический хаммам", "Оффлайн-доход 50% вместо 10%"),
    HammamStage("Хаммам вечности", "Жар никогда не падает ниже 67°"),
    HammamStage("Хаммам абсолюта", "Максимальный жар 200°"),
    HammamStage("ULTRA 6-7 ХАММАМ", "Всё бабло x6.7"),
)

enum class Skin(val title: String, val emoji: String, val price: Double, val colors: List<Color>, val bonus: Double) {
    CLASSIC("Классика", "🟢", 0.0, listOf(Color(0xFFB9F6CA), Color(0xFF00E676), Color(0xFF1B5E20)), 1.0),
    GOLD("Золото", "🟡", 1e4, listOf(Color(0xFFFFF9C4), Color(0xFFFFD600), Color(0xFFFF6F00)), 1.02),
    NEON("Неон", "🟣", 1e7, listOf(Color(0xFFF8BBD0), Color(0xFFFF4081), Color(0xFF4A148C)), 1.04),
    ICE("Лёд", "🔵", 1e10, listOf(Color(0xFFE1F5FE), Color(0xFF40C4FF), Color(0xFF01579B)), 1.06),
    LAVA("Лава", "🔴", 1e13, listOf(Color(0xFFFFF3E0), Color(0xFFFF3D00), Color(0xFF3E0000)), 1.08),
    RAINBOW("Радуга", "🌈", 1e16, listOf(Color.White, Color.Magenta, Color.Blue), 1.15),
    HAMMAM("Хаммам", "🧖", 1e22, listOf(Color(0xFFFFFFFF), Color(0xFFFFCC80), Color(0xFF6D4C41)), 1.25),
    ULTRA67("ULTRA 6-7", "6️⃣7️⃣", 6.7e25, listOf(Color(0xFFFFE57F), Color(0xFFAA00FF), Color(0xFF12005E)), 1.67),
}

enum class Achievement(val emoji: String, val title: String, val description: String, val check: (BabloGame) -> Boolean) {
    FIRST("👆", "Первый тык", "Нажать на БАБЛО", { it.totalClicks >= 1 }),
    CLICKS_67("6️⃣", "Шестьдесят семь", "67 тыков", { it.totalClicks >= 67 }),
    CLICKS_6767("7️⃣", "6767", "6 767 тыков", { it.totalClicks >= 6767 }),
    CLICKS_67K("🖐️", "Мозоль", "67 000 тыков", { it.totalClicks >= 67_000 }),
    THOUSAND("💵", "Первая тыща", "Заработать 1 000", { it.totalEarned >= 1e3 }),
    MILLION("💰", "Миллионер", "Заработать 1 млн", { it.totalEarned >= 1e6 }),
    BILLION("🏦", "Миллиардер", "Заработать 1 млрд", { it.totalEarned >= 1e9 }),
    TRILLION("🤑", "Триллионер", "Заработать 1 трлн", { it.totalEarned >= 1e12 }),
    QUINT("🪐", "Квинтиллионер", "Заработать 10¹⁸", { it.totalEarned >= 1e18 }),
    DECILL("🌌", "Почти вывод", "Заработать 10³⁰", { it.totalEarned >= 1e30 }),
    GEN_FIRST("👵", "Бизнесмен", "Купить первый генератор", { it.totalGens() >= 1 }),
    GEN_100("🌯", "Сеть шаурмичных", "100 генераторов одного типа", { g -> Gen.entries.any { g.count(it) >= 100 } }),
    GEN_TOP("⚛️", "Реакторщик", "Купить ULTRA 6-7 Реактор", { it.count(Gen.REACTOR) >= 1 }),
    PERKS_10("🛠️", "Тюнинг кнопки", "10 улучшений кнопки", { it.ownedPerks.size >= 10 }),
    PERKS_ALL("💎", "Ультимативная кнопка", "Все улучшения кнопки", { it.ownedPerks.size >= Perk.entries.size }),
    COMBO_25("🔥", "Горячие пальцы", "Комбо x25", { it.maxCombo >= 25 }),
    COMBO_67("💥", "ULTRA 6-7", "Комбо x67 — включить лихорадку", { it.maxCombo >= 67 }),
    HAMMAM_1("🚫", "Наивный", "Попытаться купить хаммам", { it.hammamAttempts >= 1 }),
    HAMMAM_67("🧱", "Упрямый", "67 раз попытаться купить хаммам", { it.hammamAttempts >= 67 }),
    HAMMAM_UP("🧖", "Пар пошёл", "Улучшить хаммам (не покупая!)", { it.hammamLevel >= 1 }),
    HAMMAM_HOT("🌡️", "Жарко!", "Разогреть хаммам до 100°", { it.temperature >= 100f }),
    HAMMAM_MAX("👑", "Хозяин пара", "Хаммам на максимум", { it.hammamLevel >= HAMMAM_STAGES.size }),
    ULTRA("⚡", "Не отпускай", "Удержать кнопку до ультра-бабло", { it.ultraUses >= 1 }),
    ULTRA_SUPER("🌈", "УЛЬТРА СУПЕР", "Включить ультра супер бабло", { it.ultraSuperUses >= 1 }),
    BUBBLES("🫧", "Ловец пузырей", "Поймать 67 пузырей", { it.bubblesPopped >= 67 }),
    BUBBLE_STREAK("🎯", "Ни одного мимо", "Серия из 25 пузырей подряд", { it.bestBubbleStreak >= 25 }),
    GOLDEN("✨", "Золотая рука", "Поймать золотой мешок", { it.goldenCaught >= 1 }),
    GOLDEN_25("🌟", "Ловец мешков", "Поймать 25 золотых мешков", { it.goldenCaught >= 25 }),
    SKIN("🎨", "Модник", "Купить скин кнопки", { it.ownedSkins.size >= 2 }),
    WITHDRAW("💳", "Вывел!", "Вывести 68 квинтиллиардов", { it.withdrawals >= 1 }),
    ALO("📞", "Ало?", "Ответить на звонок от бабла", { it.callsAnswered >= 1 }),
    ALO_67("☎️", "Колл-центр 67", "Ответить на 67 звонков", { it.callsAnswered >= 67 }),
    DEP("🎰", "Депнул", "Крутануть слоты через DEP API", { it.depSpins >= 1 }),
    DEP_JACKPOT("🤑", "Додеп окупился", "Поймать джекпот в DEP", { it.depJackpots >= 1 }),
}

data class GoldenBag(val xFrac: Float, val yFrac: Float, val expiresAt: Long)

/** Входящий звонок от бабла: звенит, пока не ответишь или не сбросится. */
data class PhoneCall(val caller: String, val expiresAt: Long)

private val CALLERS = listOf("Бабло 67", "Бабушка", "Шаурмичная", "Офшор", "Центробанк", "Хаммам", "Неизвестный 6-7")

private val CALL_LINES = listOf(
    "Ало, это бабло. Выходи, я у подъезда 💸",
    "Ало? Тебе звонит банк. У вас слишком много бабла, держите ещё.",
    "Ало, брат, ты где? Тут бабло раздают 🤝",
    "Ало-ало! Шаурма с баблом готова 🌯",
    "Ало, это хаммам. Нас всё ещё нельзя купить. Но вот тебе на пар ♨️",
    "Ало, шестьдесят семь. Повторяю: шестьдесят семь 🤷",
    "Ало, это офшор. Мы тут немного намножили, забери 🏝️",
)

/** Всё состояние игры. Хаммам купить нельзя — но его можно улучшать. Это философия. */
class BabloGame(context: Context) {
    private val prefs = context.getSharedPreferences("bablo", Context.MODE_PRIVATE)

    init {
        // v3: полный ребаланс экономики — старые сохранения с бешеными числами сбрасываем.
        if (prefs.getInt("version", 0) < SAVE_VERSION) {
            val sound = prefs.getBoolean("sound", true)
            val vibration = prefs.getBoolean("vibration", true)
            prefs.edit().clear()
                .putBoolean("sound", sound)
                .putBoolean("vibration", vibration)
                .putInt("version", SAVE_VERSION)
                .putLong("lastSeen", System.currentTimeMillis())
                .apply()
        }
    }

    private fun loadDouble(key: String) = Double.fromBits(prefs.getLong(key, 0L))

    var money by mutableDoubleStateOf(loadDouble("money"))
        private set
    var totalEarned by mutableDoubleStateOf(loadDouble("earned"))
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
    var ultraUses by mutableIntStateOf(prefs.getInt("ultraUses", 0))
        private set
    var ultraSuperUses by mutableIntStateOf(prefs.getInt("ultraSuperUses", 0))
        private set
    var bubblesPopped by mutableIntStateOf(prefs.getInt("bubbles", 0))
        private set
    var bestBubbleStreak by mutableIntStateOf(prefs.getInt("bubbleStreak", 0))
        private set
    var callsAnswered by mutableIntStateOf(prefs.getInt("calls", 0))
        private set
    var depSpins by mutableIntStateOf(prefs.getInt("depSpins", 0))
        private set
    var depJackpots by mutableIntStateOf(prefs.getInt("depJackpots", 0))
        private set
    var temperature by mutableFloatStateOf(prefs.getFloat("temp", 0f))
        private set

    var soundOn by mutableStateOf(prefs.getBoolean("sound", true))
    var vibrationOn by mutableStateOf(prefs.getBoolean("vibration", true))

    /** 1, 10 или 0 (= максимум) — сколько генераторов покупать за раз. */
    var buyAmount by mutableIntStateOf(1)

    var skin by mutableStateOf(Skin.entries.firstOrNull { it.name == prefs.getString("skin", null) } ?: Skin.CLASSIC)
    val ownedSkins = mutableStateListOf<Skin>().apply {
        add(Skin.CLASSIC)
        addAll(namesToEntries(prefs.getStringSet("skins", null), Skin.entries).filter { it != Skin.CLASSIC })
    }
    val ownedPerks = mutableStateListOf<Perk>().apply {
        addAll(namesToEntries(prefs.getStringSet("perks", null), Perk.entries))
    }
    val unlocked = mutableStateListOf<Achievement>().apply {
        addAll(namesToEntries(prefs.getStringSet("achievements", null), Achievement.entries))
    }
    /** Только что полученные достижения, UI показывает и убирает их. */
    val pendingAchievements = mutableStateListOf<Achievement>()

    private val gens = Gen.entries.associateWith { mutableIntStateOf(prefs.getInt("gen_${it.name}", 0)) }

    /** Часы игры: обновляются ~10 раз в секунду, от них зависят все таймеры. */
    var clock by mutableLongStateOf(System.currentTimeMillis())
        private set

    private var turboEnd by mutableLongStateOf(prefs.getLong("turboEnd", 0L))
    private var turboReady by mutableLongStateOf(prefs.getLong("turboReady", 0L))
    private var m67End by mutableLongStateOf(prefs.getLong("m67End", 0L))
    private var m67Ready by mutableLongStateOf(prefs.getLong("m67Ready", 0L))
    private var ultraEnd by mutableLongStateOf(prefs.getLong("ultraEnd", 0L))
    private var ultraStart = prefs.getLong("ultraStart", 0L)
    private var ultraReadyAt by mutableLongStateOf(prefs.getLong("ultraReady", 0L))
    private var superEnd by mutableLongStateOf(prefs.getLong("superEnd", 0L))
    private var superUsedThisUltra = prefs.getBoolean("superUsed", false)
    private var feverEnd by mutableLongStateOf(0L)
    private var frenzyEnd by mutableLongStateOf(0L)

    var golden by mutableStateOf<GoldenBag?>(null)
        private set
    private var nextGoldenAt = System.currentTimeMillis() + 45_000

    var call by mutableStateOf<PhoneCall?>(null)
        private set
    private var nextCallAt = System.currentTimeMillis() + 90_000

    /** API-ключ DEP ID для калькулятора депа. */
    var depKey by mutableStateOf(prefs.getString("depKey", "") ?: "")

    /** Сколько накапало, пока игрок отсутствовал; UI показывает и обнуляет. */
    var offlineGain by mutableStateOf<Double?>(null)

    private var lastClickMs = 0L
    private var autoAcc = 0.0
    private var turboAcc = 0.0
    private var boilAcc = 0.0
    private var achievementAcc = 0.0

    init {
        val away = (System.currentTimeMillis() - prefs.getLong("lastSeen", System.currentTimeMillis())).coerceIn(0, 8 * 3_600_000L)
        if (away > 60_000) {
            val gain = genPerSecondBase() * baseMultiplier() * offlineRate * away / 1000.0
            if (gain >= 1) {
                earn(gain)
                offlineGain = gain
            }
        }
    }

    // ---------- Генераторы ----------

    fun count(g: Gen): Int = gens.getValue(g).intValue

    fun totalGens(): Int = gens.values.sumOf { it.intValue }

    fun genMilestoneMult(g: Gen): Double = 2.0.pow(GEN_MILESTONES.count { count(g) >= it })

    fun nextMilestone(g: Gen): Int? = GEN_MILESTONES.firstOrNull { count(g) < it }

    /** Генератор виден, если куплен предыдущий (первый виден всегда). */
    fun genVisible(g: Gen) = g.ordinal == 0 || count(Gen.entries[g.ordinal - 1]) > 0 || count(g) > 0

    private fun costOf(g: Gen, n: Int): Double {
        val first = g.baseCost * GEN_GROWTH.pow(count(g))
        return first * (GEN_GROWTH.pow(n) - 1) / (GEN_GROWTH - 1)
    }

    fun buyCount(g: Gen): Int = if (buyAmount > 0) buyAmount else {
        val first = g.baseCost * GEN_GROWTH.pow(count(g))
        floor(ln(money * (GEN_GROWTH - 1) / first + 1) / ln(GEN_GROWTH)).toInt().coerceAtLeast(1)
    }

    fun genCost(g: Gen): Double = costOf(g, buyCount(g))

    fun canBuyGen(g: Gen) = money >= genCost(g)

    fun buyGen(g: Gen): Boolean {
        val n = buyCount(g)
        val c = costOf(g, n)
        if (money < c) return false
        money -= c
        gens.getValue(g).intValue += n
        return true
    }

    /** Доход одного генератора в секунду с учётом всех множителей. */
    fun genEach(g: Gen): Double = g.baseOut * genMilestoneMult(g) * genMultiplier

    private fun genPerSecondBase(): Double = Gen.entries.sumOf { count(it) * it.baseOut * genMilestoneMult(it) }

    // ---------- Улучшения кнопки ----------

    fun has(p: Perk) = p in ownedPerks

    fun perkAvailable(p: Perk) = !has(p) && (p.requiresPerk?.let { has(it) } ?: true)

    fun canBuyPerk(p: Perk) = perkAvailable(p) && money >= p.cost

    fun buyPerk(p: Perk): Boolean {
        if (!canBuyPerk(p)) return false
        money -= p.cost
        ownedPerks += p
        return true
    }

    // ---------- Хаммам ----------

    /** Попытка купить хаммам. Всегда неудачная. */
    fun tryBuyHammam(): String {
        hammamAttempts++
        return HAMMAM_REFUSALS[(hammamAttempts - 1) % HAMMAM_REFUSALS.size]
    }

    fun hammamHas(stage: Int) = hammamLevel >= stage

    val hammamMaxed get() = hammamLevel >= HAMMAM_STAGES.size
    val hammamCost: Double get() = 1e4 * 100.0.pow(hammamLevel)
    fun canUpgradeHammam() = !hammamMaxed && money >= hammamCost

    fun upgradeHammam(): Boolean {
        if (!canUpgradeHammam()) return false
        money -= hammamCost
        hammamLevel++
        if (hammamHas(13)) temperature = temperature.coerceAtLeast(67f)
        return true
    }

    val maxTemperature: Float get() = when {
        hammamHas(14) -> 200f
        hammamHas(8) -> 150f
        else -> 100f
    }

    val tempMult: Double get() = if (hammamHas(1)) 1.0 + temperature / 100.0 else 1.0

    val offlineRate: Double get() = if (hammamHas(12)) 0.5 else 0.1

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
    val turboDurationMs: Long get() = if (has(Perk.LONG_BUL)) 35_000L else 20_000L
    val turboCooldownMs: Long get() = 90_000L
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
    val mode67DurationMs: Long get() = if (has(Perk.SIXTH_SENSE)) 134_000L else 67_000L
    val mode67CooldownMs: Long get() = if (hammamHas(7)) 245_000L else 367_000L
    val mode67LeftMs: Long get() = (m67End - clock).coerceAtLeast(0)
    val mode67ReadyInMs: Long get() = (m67Ready - clock).coerceAtLeast(0)

    fun activate67(): Boolean {
        val now = System.currentTimeMillis()
        if (now < m67Ready) return false
        m67End = now + mode67DurationMs
        m67Ready = m67End + mode67CooldownMs
        clock = now
        return true
    }

    /**
     * Ультра-бабло нельзя включить кнопкой: оно включается само, если долго держать БАБЛО.
     * А если держать и дальше, пока оно работает — включается ультра супер бабло.
     */
    val ultraBablo: Boolean get() = clock < ultraEnd
    val ultraSuper: Boolean get() = clock < superEnd
    val ultraLeftMs: Long get() = (ultraEnd - clock).coerceAtLeast(0)
    val superLeftMs: Long get() = (superEnd - clock).coerceAtLeast(0)
    val ultraReadyInMs: Long get() = (ultraReadyAt - clock).coerceAtLeast(0)

    private val holdSpeed: Int get() = (if (has(Perk.ACCU)) 2 else 1) * (if (hammamHas(10)) 2 else 1)
    val ultraHoldMs: Long get() = ULTRA_HOLD / holdSpeed
    val superHoldMs: Long get() = SUPER_HOLD / holdSpeed

    enum class HoldEvent { NONE, ULTRA, SUPER }

    /** Сколько осталось держать: 0..1 для шкалы на кнопке. */
    fun holdProgress(pressStart: Long, pressed: Boolean): Float {
        if (!pressed) return 0f
        val now = System.currentTimeMillis()
        return when {
            ultraBablo && !superUsedThisUltra ->
                (now - maxOf(pressStart, ultraStart)).toFloat() / superHoldMs
            !ultraBablo && now >= ultraReadyAt -> (now - pressStart).toFloat() / ultraHoldMs
            else -> 0f
        }.coerceIn(0f, 1f)
    }

    /** Вызывается каждый кадр, пока кнопка зажата. */
    fun onHold(pressStart: Long): HoldEvent {
        val now = System.currentTimeMillis()
        if (!ultraBablo && now >= ultraReadyAt && now - pressStart >= ultraHoldMs) {
            ultraStart = now
            ultraEnd = now + ULTRA_DURATION
            ultraReadyAt = ultraEnd + ULTRA_COOLDOWN
            superUsedThisUltra = false
            ultraUses++
            clock = now
            return HoldEvent.ULTRA
        }
        if (ultraBablo && !superUsedThisUltra && now - maxOf(pressStart, ultraStart) >= superHoldMs) {
            superUsedThisUltra = true
            superEnd = minOf(now + SUPER_DURATION, ultraEnd + SUPER_DURATION)
            ultraEnd = maxOf(ultraEnd, superEnd)
            ultraReadyAt = ultraEnd + ULTRA_COOLDOWN
            ultraSuperUses++
            clock = now
            return HoldEvent.SUPER
        }
        return HoldEvent.NONE
    }

    // ---------- Пузыри турбо-выбулькивателя ----------

    var bubbleStreak by mutableIntStateOf(0)
        private set

    /** Пойманный пузырь. Золотой — в 5 раз жирнее. */
    fun popBubble(goldenBubble: Boolean): Double {
        bubblesPopped++
        bubbleStreak++
        if (bubbleStreak > bestBubbleStreak) bestBubbleStreak = bubbleStreak
        var gain = perClick * 4 * (1.0 + 0.1 * bubbleStreak.coerceAtMost(30))
        if (has(Perk.RESONANCE)) gain += perSecond * 3
        if (goldenBubble) gain *= 5
        earn(gain)
        return gain
    }

    /** Пузырь улетел — серия сгорает. */
    fun missBubble() {
        bubbleStreak = 0
    }

    /** ULTRA 6-7 лихорадка: включается на каждом комбо, кратном 67. */
    val fever: Boolean get() = clock < feverEnd
    val feverLeftMs: Long get() = (feverEnd - clock).coerceAtLeast(0)
    private val feverMult: Double get() = if (has(Perk.ULTRA_FINGER)) 67.0 else 6.7

    /** Бабло-безумие от золотого мешка. */
    val frenzy: Boolean get() = clock < frenzyEnd

    // ---------- Экономика ----------

    /** Постоянные множители: жетоны, скин, ачивки, хаммам. */
    private fun baseMultiplier(): Double {
        var m = (1.0 + 0.67 * tokens) * skin.bonus * (1.0 + 0.01 * unlocked.size) * 1.5.pow(hammamLevel)
        if (hammamHas(15)) m *= 6.7
        return m
    }

    /** Все множители, включая временные режимы. */
    val multiplier: Double
        get() {
            var m = baseMultiplier()
            if (mode67) m *= 6.7
            if (ultraSuper) m *= 25.0 else if (ultraBablo) m *= 5.0
            if (fever) m *= feverMult
            if (frenzy) m *= 7.0
            return m
        }

    private val genMultiplier: Double get() = multiplier * (if (hammamHas(4)) tempMult else 1.0)

    val perSecond: Double get() = genPerSecondBase() * genMultiplier

    private val clickBase: Double
        get() {
            var c = 1.0
            if (has(Perk.SAUSAGE)) c *= 2
            if (has(Perk.TITAN)) c *= 2
            if (has(Perk.SIZE)) c *= 2
            if (has(Perk.GOLD67)) c *= 6.7
            return c
        }

    private val clickPct: Double
        get() = (if (has(Perk.PCT1)) 0.01 else 0.0) + (if (has(Perk.PCT2)) 0.02 else 0.0) + (if (has(Perk.PCT3)) 0.03 else 0.0)

    val perClick: Double get() = clickBase * multiplier * tempMult + clickPct * perSecond

    val critChance: Float
        get() = (if (has(Perk.CRIT)) 0.05f else 0f) + (if (has(Perk.MANIA)) 0.07f else 0f) +
            (if (hammamHas(3) && temperature >= 67f) 0.10f else 0f)

    private val critMult: Double get() = if (has(Perk.SHARP)) 25.0 else 10.0

    val comboBonus: Double get() = combo.coerceAtMost(67) * (if (has(Perk.COMBO)) 0.02 else 0.01)

    private val comboWindowMs: Long get() = if (has(Perk.COMBO)) 700L else 450L
    private val comboGraceMs: Long get() = if (hammamHas(9)) 2_000L else 900L

    val holdRate: Double get() = if (has(Perk.HOLD)) 12.0 else 6.0

    val buttonScale: Float get() = if (has(Perk.SIZE)) 1.2f else 1f

    private fun earn(x: Double) {
        money += x
        totalEarned += x
    }

    data class ClickResult(
        val gain: Double,
        val crit: Boolean,
        val combo: Int,
        val fever: Boolean,
        val echo: Boolean,
        val prize: String?,
        val goldenSpawned: Boolean,
    )

    fun click(auto: Boolean = false): ClickResult {
        val now = System.currentTimeMillis()
        var feverStarted = false
        var prize: String? = null
        if (!auto) {
            combo = if (now - lastClickMs < comboWindowMs) (combo + 1).coerceAtMost(6767) else 1
            lastClickMs = now
            totalClicks++
            if (combo > maxCombo) maxCombo = combo
            if (combo % 67 == 0) {
                feverEnd = now + if (hammamHas(11)) FEVER_DURATION * 2 else FEVER_DURATION
                clock = now
                feverStarted = true
            }
            if (hammamHas(1)) temperature = (temperature + 0.7f).coerceAtMost(maxTemperature)
            if (has(Perk.ROULETTE) && totalClicks % 67 == 0) prize = spinRoulette()
        }
        val crit = !auto && Random.nextFloat() < critChance
        val echo = !auto && has(Perk.ECHO) && Random.nextFloat() < 0.1f
        var gain = perClick * (1.0 + comboBonus)
        if (crit) gain *= critMult
        if (echo) gain *= 3.0
        if (auto) gain *= 0.5
        earn(gain)
        var spawned = false
        if (crit && has(Perk.SHAWARMA_CRIT) && golden == null && Random.nextFloat() < 0.03f) {
            spawnGolden(now)
            spawned = true
        }
        return ClickResult(gain, crit, combo, feverStarted, echo, prize, spawned)
    }

    private fun spinRoulette(): String = when (Random.nextInt(3)) {
        0 -> {
            if (golden == null) spawnGolden(System.currentTimeMillis())
            "🎰 Золотой мешок!"
        }
        1 -> {
            combo += 20
            "🎰 Комбо +20!"
        }
        else -> {
            val x = perSecond * 60 + perClick * 67
            earn(x)
            "🎰 +${formatBablo(x)}"
        }
    }

    // ---------- Золотой мешок ----------

    data class GoldenReward(val text: String, val amount: Double)

    fun catchGolden(): GoldenReward? {
        golden ?: return null
        golden = null
        goldenCaught++
        scheduleGolden()
        return if (Random.nextBoolean()) {
            frenzyEnd = System.currentTimeMillis() + 30_000
            GoldenReward("БАБЛО-БЕЗУМИЕ x7 на 30 сек!", 0.0)
        } else {
            val amount = minOf(money * 0.15, (perSecond + perClick * 5) * 900) + 13
            earn(amount)
            GoldenReward("БАБЛО-ФОНТАН +${formatBablo(amount)}", amount)
        }
    }

    private fun spawnGolden(now: Long) {
        val life = if (has(Perk.MAGNET)) 15_000L else 9_000L
        golden = GoldenBag(0.1f + Random.nextFloat() * 0.75f, 0.25f + Random.nextFloat() * 0.5f, now + life)
    }

    private fun scheduleGolden() {
        var delay = 60_000.0 + Random.nextDouble(120_000.0)
        if (has(Perk.MAGNET)) delay /= 1.5
        if (hammamHas(5)) delay /= 2
        nextGoldenAt = System.currentTimeMillis() + delay.toLong()
    }

    // ---------- 📞 Ало ----------

    data class CallReward(val line: String, val amount: Double)

    fun answerCall(): CallReward? {
        call ?: return null
        call = null
        callsAnswered++
        scheduleCall()
        val amount = (perSecond * 120 + perClick * 67) * (if (Random.nextInt(67) == 0) 67 else 1) + 67
        earn(amount)
        return CallReward(CALL_LINES.random(), amount)
    }

    fun declineCall() {
        call = null
        scheduleCall()
    }

    private fun scheduleCall() {
        nextCallAt = System.currentTimeMillis() + 120_000 + Random.nextLong(180_000)
    }

    // ---------- 🎰 DEP ----------

    /** Награда в бабле за спин в DEP: чем жирнее выигрыш относительно ставки, тем больше. */
    fun depReward(result: String, bet: Long, win: Long): Double {
        depSpins++
        if (result == "jackpot") {
            depJackpots++
            frenzyEnd = System.currentTimeMillis() + 30_000
        }
        val ratio = if (bet > 0) win.toDouble() / bet else 0.0
        val amount = (perSecond * 60 + perClick * 10) * (0.5 + ratio) + 6.7
        earn(amount)
        return amount
    }

    // ---------- Вывод (престиж) ----------

    val canWithdraw get() = money >= WITHDRAW_MIN
    val withdrawProgress: Float get() = (log10(money.coerceAtLeast(1.0)) / log10(WITHDRAW_MIN)).toFloat().coerceIn(0f, 1f)
    val tokensForWithdraw: Long get() = if (!canWithdraw) 0 else 1 + floor(log10(money / WITHDRAW_MIN)).toLong()

    /** Выводит всё бабло, сбрасывает генераторы и улучшения кнопки и выдаёт жетоны 67. Хаммам остаётся. */
    fun withdraw(): Long {
        if (!canWithdraw) return 0
        val gained = tokensForWithdraw
        tokens += gained
        withdrawals++
        money = 0.0
        gens.values.forEach { it.intValue = 0 }
        ownedPerks.clear()
        return gained
    }

    // ---------- Тик ----------

    /** bubbles — сколько пузырей выпустить в этом кадре. */
    data class TickResult(val bubbles: Int, val boiled: Double)

    fun tick(dtSec: Double): TickResult {
        val now = System.currentTimeMillis()
        if (now - clock >= 100) clock = now
        if (now - lastClickMs > comboGraceMs && combo > 0) combo = 0

        autoAcc += perSecond * dtSec
        if (autoAcc >= 1.0 || autoAcc > perSecond * 0.2) {
            earn(autoAcc)
            autoAcc = 0.0
        }

        if (hammamHas(1)) {
            val floorTemp = if (hammamHas(13)) 67f else 0f
            val decay = if (hammamHas(2)) 2f else 4f
            if (temperature > floorTemp) temperature = (temperature - decay * dtSec.toFloat()).coerceAtLeast(floorTemp)
        }

        var boiled = 0.0
        if (hammamHas(6) && temperature >= 90f) {
            boilAcc += dtSec
            if (boilAcc >= 10.0) {
                boilAcc = 0.0
                boiled = perSecond * 10
                earn(boiled)
            }
        } else {
            boilAcc = 0.0
        }

        val g = golden
        if (g == null && now >= nextGoldenAt) {
            spawnGolden(now)
        } else if (g != null && now > g.expiresAt) {
            golden = null
            scheduleGolden()
        }

        val c = call
        if (c == null && now >= nextCallAt && golden == null) {
            call = PhoneCall(CALLERS.random(), now + 12_000)
        } else if (c != null && now > c.expiresAt) {
            declineCall()
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

        var bubbles = 0
        if (turbo) {
            turboAcc += (if (has(Perk.LONG_BUL)) TURBO_RATE * 1.5 else TURBO_RATE) * dtSec
            bubbles = turboAcc.toInt()
            turboAcc -= bubbles
        }
        return TickResult(bubbles, boiled)
    }

    fun save() {
        prefs.edit().apply {
            putInt("version", SAVE_VERSION)
            putLong("lastSeen", System.currentTimeMillis())
            putLong("money", money.toRawBits())
            putLong("earned", totalEarned.toRawBits())
            putInt("clicks", totalClicks)
            putInt("maxCombo", maxCombo)
            putInt("hammam", hammamAttempts)
            putInt("hammamLevel", hammamLevel)
            putLong("tokens", tokens)
            putInt("withdrawals", withdrawals)
            putInt("golden", goldenCaught)
            putInt("ultraUses", ultraUses)
            putInt("ultraSuperUses", ultraSuperUses)
            putInt("bubbles", bubblesPopped)
            putInt("bubbleStreak", bestBubbleStreak)
            putInt("calls", callsAnswered)
            putInt("depSpins", depSpins)
            putInt("depJackpots", depJackpots)
            putString("depKey", depKey)
            putLong("ultraStart", ultraStart)
            putLong("ultraReady", ultraReadyAt)
            putLong("superEnd", superEnd)
            putBoolean("superUsed", superUsedThisUltra)
            putFloat("temp", temperature)
            putBoolean("sound", soundOn)
            putBoolean("vibration", vibrationOn)
            putString("skin", skin.name)
            putStringSet("skins", ownedSkins.map { it.name }.toSet())
            putStringSet("perks", ownedPerks.map { it.name }.toSet())
            putStringSet("achievements", unlocked.map { it.name }.toSet())
            putLong("turboEnd", turboEnd)
            putLong("turboReady", turboReady)
            putLong("m67End", m67End)
            putLong("m67Ready", m67Ready)
            putLong("ultraEnd", ultraEnd)
            gens.forEach { (g, s) -> putInt("gen_${g.name}", s.intValue) }
        }.apply()
    }

    companion object {
        const val SAVE_VERSION = 3
        const val GEN_GROWTH = 1.15
        const val TURBO_RATE = 2.5
        const val FEVER_DURATION = 6_700L
        const val ULTRA_HOLD = 6_700L
        const val SUPER_HOLD = 6_700L
        const val ULTRA_DURATION = 30_000L
        const val ULTRA_COOLDOWN = 60_000L
        const val SUPER_DURATION = 15_000L

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

private fun <T : Enum<T>> namesToEntries(names: Set<String>?, entries: List<T>): List<T> =
    names.orEmpty().mapNotNull { n -> entries.firstOrNull { it.name == n } }

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
