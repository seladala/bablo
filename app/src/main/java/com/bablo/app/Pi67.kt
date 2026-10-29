package com.bablo.app

import kotlin.math.sqrt

/*
 * 67 способов посчитать число π. Каждая функция честно считает пи своим рядом.
 * Ультимативная математика бабла: чем больше пи, тем больше бабла.
 */

/** π по ряду «Лейбниц», 67 шагов. */
fun pi67_0(): Double {
    var s = 0.0
    for (k in 0 until 67) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 67 шагов. */
fun pi67_1(): Double {
    var s = 3.0
    for (k in 1..67) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 67 шагов. */
fun pi67_2(): Double {
    var p = 1.0
    for (k in 1..67) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 67 шагов. */
fun pi67_3(): Double {
    var s = 0.0
    for (k in 1..67) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 67 шагов. */
fun pi67_4(): Double {
    var s = 0.0
    for (k in 0 until 67) { val x = (k + 0.5) / 67; s += 4.0 / (1 + x * x) }
    return s / 67
}

/** π по ряду «Эйлер-67», 67 шагов. */
fun pi67_5(): Double {
    var s = 0.0
    for (k in 1..67) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 134 шагов. */
fun pi67_6(): Double {
    var s = 0.0
    for (k in 0 until 134) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 134 шагов. */
fun pi67_7(): Double {
    var s = 3.0
    for (k in 1..134) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 134 шагов. */
fun pi67_8(): Double {
    var p = 1.0
    for (k in 1..134) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 134 шагов. */
fun pi67_9(): Double {
    var s = 0.0
    for (k in 1..134) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 134 шагов. */
fun pi67_10(): Double {
    var s = 0.0
    for (k in 0 until 134) { val x = (k + 0.5) / 134; s += 4.0 / (1 + x * x) }
    return s / 134
}

/** π по ряду «Эйлер-67», 134 шагов. */
fun pi67_11(): Double {
    var s = 0.0
    for (k in 1..134) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 201 шагов. */
fun pi67_12(): Double {
    var s = 0.0
    for (k in 0 until 201) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 201 шагов. */
fun pi67_13(): Double {
    var s = 3.0
    for (k in 1..201) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 201 шагов. */
fun pi67_14(): Double {
    var p = 1.0
    for (k in 1..201) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 201 шагов. */
fun pi67_15(): Double {
    var s = 0.0
    for (k in 1..201) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 201 шагов. */
fun pi67_16(): Double {
    var s = 0.0
    for (k in 0 until 201) { val x = (k + 0.5) / 201; s += 4.0 / (1 + x * x) }
    return s / 201
}

/** π по ряду «Эйлер-67», 201 шагов. */
fun pi67_17(): Double {
    var s = 0.0
    for (k in 1..201) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 268 шагов. */
fun pi67_18(): Double {
    var s = 0.0
    for (k in 0 until 268) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 268 шагов. */
fun pi67_19(): Double {
    var s = 3.0
    for (k in 1..268) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 268 шагов. */
fun pi67_20(): Double {
    var p = 1.0
    for (k in 1..268) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 268 шагов. */
fun pi67_21(): Double {
    var s = 0.0
    for (k in 1..268) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 268 шагов. */
fun pi67_22(): Double {
    var s = 0.0
    for (k in 0 until 268) { val x = (k + 0.5) / 268; s += 4.0 / (1 + x * x) }
    return s / 268
}

/** π по ряду «Эйлер-67», 268 шагов. */
fun pi67_23(): Double {
    var s = 0.0
    for (k in 1..268) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 335 шагов. */
fun pi67_24(): Double {
    var s = 0.0
    for (k in 0 until 335) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 335 шагов. */
fun pi67_25(): Double {
    var s = 3.0
    for (k in 1..335) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 335 шагов. */
fun pi67_26(): Double {
    var p = 1.0
    for (k in 1..335) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 335 шагов. */
fun pi67_27(): Double {
    var s = 0.0
    for (k in 1..335) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 335 шагов. */
fun pi67_28(): Double {
    var s = 0.0
    for (k in 0 until 335) { val x = (k + 0.5) / 335; s += 4.0 / (1 + x * x) }
    return s / 335
}

/** π по ряду «Эйлер-67», 335 шагов. */
fun pi67_29(): Double {
    var s = 0.0
    for (k in 1..335) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 402 шагов. */
fun pi67_30(): Double {
    var s = 0.0
    for (k in 0 until 402) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 402 шагов. */
fun pi67_31(): Double {
    var s = 3.0
    for (k in 1..402) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 402 шагов. */
fun pi67_32(): Double {
    var p = 1.0
    for (k in 1..402) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 402 шагов. */
fun pi67_33(): Double {
    var s = 0.0
    for (k in 1..402) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 402 шагов. */
fun pi67_34(): Double {
    var s = 0.0
    for (k in 0 until 402) { val x = (k + 0.5) / 402; s += 4.0 / (1 + x * x) }
    return s / 402
}

/** π по ряду «Эйлер-67», 402 шагов. */
fun pi67_35(): Double {
    var s = 0.0
    for (k in 1..402) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 469 шагов. */
fun pi67_36(): Double {
    var s = 0.0
    for (k in 0 until 469) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 469 шагов. */
fun pi67_37(): Double {
    var s = 3.0
    for (k in 1..469) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 469 шагов. */
fun pi67_38(): Double {
    var p = 1.0
    for (k in 1..469) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 469 шагов. */
fun pi67_39(): Double {
    var s = 0.0
    for (k in 1..469) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 469 шагов. */
fun pi67_40(): Double {
    var s = 0.0
    for (k in 0 until 469) { val x = (k + 0.5) / 469; s += 4.0 / (1 + x * x) }
    return s / 469
}

/** π по ряду «Эйлер-67», 469 шагов. */
fun pi67_41(): Double {
    var s = 0.0
    for (k in 1..469) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 536 шагов. */
fun pi67_42(): Double {
    var s = 0.0
    for (k in 0 until 536) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 536 шагов. */
fun pi67_43(): Double {
    var s = 3.0
    for (k in 1..536) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 536 шагов. */
fun pi67_44(): Double {
    var p = 1.0
    for (k in 1..536) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 536 шагов. */
fun pi67_45(): Double {
    var s = 0.0
    for (k in 1..536) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 536 шагов. */
fun pi67_46(): Double {
    var s = 0.0
    for (k in 0 until 536) { val x = (k + 0.5) / 536; s += 4.0 / (1 + x * x) }
    return s / 536
}

/** π по ряду «Эйлер-67», 536 шагов. */
fun pi67_47(): Double {
    var s = 0.0
    for (k in 1..536) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 603 шагов. */
fun pi67_48(): Double {
    var s = 0.0
    for (k in 0 until 603) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 603 шагов. */
fun pi67_49(): Double {
    var s = 3.0
    for (k in 1..603) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 603 шагов. */
fun pi67_50(): Double {
    var p = 1.0
    for (k in 1..603) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 603 шагов. */
fun pi67_51(): Double {
    var s = 0.0
    for (k in 1..603) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 603 шагов. */
fun pi67_52(): Double {
    var s = 0.0
    for (k in 0 until 603) { val x = (k + 0.5) / 603; s += 4.0 / (1 + x * x) }
    return s / 603
}

/** π по ряду «Эйлер-67», 603 шагов. */
fun pi67_53(): Double {
    var s = 0.0
    for (k in 1..603) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 670 шагов. */
fun pi67_54(): Double {
    var s = 0.0
    for (k in 0 until 670) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 670 шагов. */
fun pi67_55(): Double {
    var s = 3.0
    for (k in 1..670) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 670 шагов. */
fun pi67_56(): Double {
    var p = 1.0
    for (k in 1..670) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 670 шагов. */
fun pi67_57(): Double {
    var s = 0.0
    for (k in 1..670) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 670 шагов. */
fun pi67_58(): Double {
    var s = 0.0
    for (k in 0 until 670) { val x = (k + 0.5) / 670; s += 4.0 / (1 + x * x) }
    return s / 670
}

/** π по ряду «Эйлер-67», 670 шагов. */
fun pi67_59(): Double {
    var s = 0.0
    for (k in 1..670) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 737 шагов. */
fun pi67_60(): Double {
    var s = 0.0
    for (k in 0 until 737) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 737 шагов. */
fun pi67_61(): Double {
    var s = 3.0
    for (k in 1..737) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 737 шагов. */
fun pi67_62(): Double {
    var p = 1.0
    for (k in 1..737) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 737 шагов. */
fun pi67_63(): Double {
    var s = 0.0
    for (k in 1..737) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 737 шагов. */
fun pi67_64(): Double {
    var s = 0.0
    for (k in 0 until 737) { val x = (k + 0.5) / 737; s += 4.0 / (1 + x * x) }
    return s / 737
}

/** π по ряду «Эйлер-67», 737 шагов. */
fun pi67_65(): Double {
    var s = 0.0
    for (k in 1..737) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 804 шагов. */
fun pi67_66(): Double {
    var s = 0.0
    for (k in 0 until 804) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 804 шагов. */
fun pi67_67(): Double {
    var s = 3.0
    for (k in 1..804) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 804 шагов. */
fun pi67_68(): Double {
    var p = 1.0
    for (k in 1..804) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 804 шагов. */
fun pi67_69(): Double {
    var s = 0.0
    for (k in 1..804) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 804 шагов. */
fun pi67_70(): Double {
    var s = 0.0
    for (k in 0 until 804) { val x = (k + 0.5) / 804; s += 4.0 / (1 + x * x) }
    return s / 804
}

/** π по ряду «Эйлер-67», 804 шагов. */
fun pi67_71(): Double {
    var s = 0.0
    for (k in 1..804) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 871 шагов. */
fun pi67_72(): Double {
    var s = 0.0
    for (k in 0 until 871) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 871 шагов. */
fun pi67_73(): Double {
    var s = 3.0
    for (k in 1..871) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 871 шагов. */
fun pi67_74(): Double {
    var p = 1.0
    for (k in 1..871) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 871 шагов. */
fun pi67_75(): Double {
    var s = 0.0
    for (k in 1..871) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 871 шагов. */
fun pi67_76(): Double {
    var s = 0.0
    for (k in 0 until 871) { val x = (k + 0.5) / 871; s += 4.0 / (1 + x * x) }
    return s / 871
}

/** π по ряду «Эйлер-67», 871 шагов. */
fun pi67_77(): Double {
    var s = 0.0
    for (k in 1..871) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 938 шагов. */
fun pi67_78(): Double {
    var s = 0.0
    for (k in 0 until 938) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 938 шагов. */
fun pi67_79(): Double {
    var s = 3.0
    for (k in 1..938) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 938 шагов. */
fun pi67_80(): Double {
    var p = 1.0
    for (k in 1..938) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 938 шагов. */
fun pi67_81(): Double {
    var s = 0.0
    for (k in 1..938) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 938 шагов. */
fun pi67_82(): Double {
    var s = 0.0
    for (k in 0 until 938) { val x = (k + 0.5) / 938; s += 4.0 / (1 + x * x) }
    return s / 938
}

/** π по ряду «Эйлер-67», 938 шагов. */
fun pi67_83(): Double {
    var s = 0.0
    for (k in 1..938) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1005 шагов. */
fun pi67_84(): Double {
    var s = 0.0
    for (k in 0 until 1005) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1005 шагов. */
fun pi67_85(): Double {
    var s = 3.0
    for (k in 1..1005) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1005 шагов. */
fun pi67_86(): Double {
    var p = 1.0
    for (k in 1..1005) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1005 шагов. */
fun pi67_87(): Double {
    var s = 0.0
    for (k in 1..1005) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1005 шагов. */
fun pi67_88(): Double {
    var s = 0.0
    for (k in 0 until 1005) { val x = (k + 0.5) / 1005; s += 4.0 / (1 + x * x) }
    return s / 1005
}

/** π по ряду «Эйлер-67», 1005 шагов. */
fun pi67_89(): Double {
    var s = 0.0
    for (k in 1..1005) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1072 шагов. */
fun pi67_90(): Double {
    var s = 0.0
    for (k in 0 until 1072) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1072 шагов. */
fun pi67_91(): Double {
    var s = 3.0
    for (k in 1..1072) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1072 шагов. */
fun pi67_92(): Double {
    var p = 1.0
    for (k in 1..1072) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1072 шагов. */
fun pi67_93(): Double {
    var s = 0.0
    for (k in 1..1072) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1072 шагов. */
fun pi67_94(): Double {
    var s = 0.0
    for (k in 0 until 1072) { val x = (k + 0.5) / 1072; s += 4.0 / (1 + x * x) }
    return s / 1072
}

/** π по ряду «Эйлер-67», 1072 шагов. */
fun pi67_95(): Double {
    var s = 0.0
    for (k in 1..1072) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1139 шагов. */
fun pi67_96(): Double {
    var s = 0.0
    for (k in 0 until 1139) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1139 шагов. */
fun pi67_97(): Double {
    var s = 3.0
    for (k in 1..1139) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1139 шагов. */
fun pi67_98(): Double {
    var p = 1.0
    for (k in 1..1139) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1139 шагов. */
fun pi67_99(): Double {
    var s = 0.0
    for (k in 1..1139) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1139 шагов. */
fun pi67_100(): Double {
    var s = 0.0
    for (k in 0 until 1139) { val x = (k + 0.5) / 1139; s += 4.0 / (1 + x * x) }
    return s / 1139
}

/** π по ряду «Эйлер-67», 1139 шагов. */
fun pi67_101(): Double {
    var s = 0.0
    for (k in 1..1139) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1206 шагов. */
fun pi67_102(): Double {
    var s = 0.0
    for (k in 0 until 1206) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1206 шагов. */
fun pi67_103(): Double {
    var s = 3.0
    for (k in 1..1206) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1206 шагов. */
fun pi67_104(): Double {
    var p = 1.0
    for (k in 1..1206) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1206 шагов. */
fun pi67_105(): Double {
    var s = 0.0
    for (k in 1..1206) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1206 шагов. */
fun pi67_106(): Double {
    var s = 0.0
    for (k in 0 until 1206) { val x = (k + 0.5) / 1206; s += 4.0 / (1 + x * x) }
    return s / 1206
}

/** π по ряду «Эйлер-67», 1206 шагов. */
fun pi67_107(): Double {
    var s = 0.0
    for (k in 1..1206) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1273 шагов. */
fun pi67_108(): Double {
    var s = 0.0
    for (k in 0 until 1273) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1273 шагов. */
fun pi67_109(): Double {
    var s = 3.0
    for (k in 1..1273) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1273 шагов. */
fun pi67_110(): Double {
    var p = 1.0
    for (k in 1..1273) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1273 шагов. */
fun pi67_111(): Double {
    var s = 0.0
    for (k in 1..1273) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1273 шагов. */
fun pi67_112(): Double {
    var s = 0.0
    for (k in 0 until 1273) { val x = (k + 0.5) / 1273; s += 4.0 / (1 + x * x) }
    return s / 1273
}

/** π по ряду «Эйлер-67», 1273 шагов. */
fun pi67_113(): Double {
    var s = 0.0
    for (k in 1..1273) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1340 шагов. */
fun pi67_114(): Double {
    var s = 0.0
    for (k in 0 until 1340) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1340 шагов. */
fun pi67_115(): Double {
    var s = 3.0
    for (k in 1..1340) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1340 шагов. */
fun pi67_116(): Double {
    var p = 1.0
    for (k in 1..1340) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1340 шагов. */
fun pi67_117(): Double {
    var s = 0.0
    for (k in 1..1340) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1340 шагов. */
fun pi67_118(): Double {
    var s = 0.0
    for (k in 0 until 1340) { val x = (k + 0.5) / 1340; s += 4.0 / (1 + x * x) }
    return s / 1340
}

/** π по ряду «Эйлер-67», 1340 шагов. */
fun pi67_119(): Double {
    var s = 0.0
    for (k in 1..1340) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1407 шагов. */
fun pi67_120(): Double {
    var s = 0.0
    for (k in 0 until 1407) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1407 шагов. */
fun pi67_121(): Double {
    var s = 3.0
    for (k in 1..1407) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1407 шагов. */
fun pi67_122(): Double {
    var p = 1.0
    for (k in 1..1407) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1407 шагов. */
fun pi67_123(): Double {
    var s = 0.0
    for (k in 1..1407) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1407 шагов. */
fun pi67_124(): Double {
    var s = 0.0
    for (k in 0 until 1407) { val x = (k + 0.5) / 1407; s += 4.0 / (1 + x * x) }
    return s / 1407
}

/** π по ряду «Эйлер-67», 1407 шагов. */
fun pi67_125(): Double {
    var s = 0.0
    for (k in 1..1407) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1474 шагов. */
fun pi67_126(): Double {
    var s = 0.0
    for (k in 0 until 1474) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1474 шагов. */
fun pi67_127(): Double {
    var s = 3.0
    for (k in 1..1474) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1474 шагов. */
fun pi67_128(): Double {
    var p = 1.0
    for (k in 1..1474) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1474 шагов. */
fun pi67_129(): Double {
    var s = 0.0
    for (k in 1..1474) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1474 шагов. */
fun pi67_130(): Double {
    var s = 0.0
    for (k in 0 until 1474) { val x = (k + 0.5) / 1474; s += 4.0 / (1 + x * x) }
    return s / 1474
}

/** π по ряду «Эйлер-67», 1474 шагов. */
fun pi67_131(): Double {
    var s = 0.0
    for (k in 1..1474) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1541 шагов. */
fun pi67_132(): Double {
    var s = 0.0
    for (k in 0 until 1541) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1541 шагов. */
fun pi67_133(): Double {
    var s = 3.0
    for (k in 1..1541) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1541 шагов. */
fun pi67_134(): Double {
    var p = 1.0
    for (k in 1..1541) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1541 шагов. */
fun pi67_135(): Double {
    var s = 0.0
    for (k in 1..1541) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1541 шагов. */
fun pi67_136(): Double {
    var s = 0.0
    for (k in 0 until 1541) { val x = (k + 0.5) / 1541; s += 4.0 / (1 + x * x) }
    return s / 1541
}

/** π по ряду «Эйлер-67», 1541 шагов. */
fun pi67_137(): Double {
    var s = 0.0
    for (k in 1..1541) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1608 шагов. */
fun pi67_138(): Double {
    var s = 0.0
    for (k in 0 until 1608) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1608 шагов. */
fun pi67_139(): Double {
    var s = 3.0
    for (k in 1..1608) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1608 шагов. */
fun pi67_140(): Double {
    var p = 1.0
    for (k in 1..1608) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1608 шагов. */
fun pi67_141(): Double {
    var s = 0.0
    for (k in 1..1608) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1608 шагов. */
fun pi67_142(): Double {
    var s = 0.0
    for (k in 0 until 1608) { val x = (k + 0.5) / 1608; s += 4.0 / (1 + x * x) }
    return s / 1608
}

/** π по ряду «Эйлер-67», 1608 шагов. */
fun pi67_143(): Double {
    var s = 0.0
    for (k in 1..1608) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1675 шагов. */
fun pi67_144(): Double {
    var s = 0.0
    for (k in 0 until 1675) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1675 шагов. */
fun pi67_145(): Double {
    var s = 3.0
    for (k in 1..1675) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1675 шагов. */
fun pi67_146(): Double {
    var p = 1.0
    for (k in 1..1675) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1675 шагов. */
fun pi67_147(): Double {
    var s = 0.0
    for (k in 1..1675) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1675 шагов. */
fun pi67_148(): Double {
    var s = 0.0
    for (k in 0 until 1675) { val x = (k + 0.5) / 1675; s += 4.0 / (1 + x * x) }
    return s / 1675
}

/** π по ряду «Эйлер-67», 1675 шагов. */
fun pi67_149(): Double {
    var s = 0.0
    for (k in 1..1675) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1742 шагов. */
fun pi67_150(): Double {
    var s = 0.0
    for (k in 0 until 1742) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1742 шагов. */
fun pi67_151(): Double {
    var s = 3.0
    for (k in 1..1742) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1742 шагов. */
fun pi67_152(): Double {
    var p = 1.0
    for (k in 1..1742) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1742 шагов. */
fun pi67_153(): Double {
    var s = 0.0
    for (k in 1..1742) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1742 шагов. */
fun pi67_154(): Double {
    var s = 0.0
    for (k in 0 until 1742) { val x = (k + 0.5) / 1742; s += 4.0 / (1 + x * x) }
    return s / 1742
}

/** π по ряду «Эйлер-67», 1742 шагов. */
fun pi67_155(): Double {
    var s = 0.0
    for (k in 1..1742) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1809 шагов. */
fun pi67_156(): Double {
    var s = 0.0
    for (k in 0 until 1809) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1809 шагов. */
fun pi67_157(): Double {
    var s = 3.0
    for (k in 1..1809) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1809 шагов. */
fun pi67_158(): Double {
    var p = 1.0
    for (k in 1..1809) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1809 шагов. */
fun pi67_159(): Double {
    var s = 0.0
    for (k in 1..1809) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1809 шагов. */
fun pi67_160(): Double {
    var s = 0.0
    for (k in 0 until 1809) { val x = (k + 0.5) / 1809; s += 4.0 / (1 + x * x) }
    return s / 1809
}

/** π по ряду «Эйлер-67», 1809 шагов. */
fun pi67_161(): Double {
    var s = 0.0
    for (k in 1..1809) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1876 шагов. */
fun pi67_162(): Double {
    var s = 0.0
    for (k in 0 until 1876) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1876 шагов. */
fun pi67_163(): Double {
    var s = 3.0
    for (k in 1..1876) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1876 шагов. */
fun pi67_164(): Double {
    var p = 1.0
    for (k in 1..1876) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1876 шагов. */
fun pi67_165(): Double {
    var s = 0.0
    for (k in 1..1876) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1876 шагов. */
fun pi67_166(): Double {
    var s = 0.0
    for (k in 0 until 1876) { val x = (k + 0.5) / 1876; s += 4.0 / (1 + x * x) }
    return s / 1876
}

/** π по ряду «Эйлер-67», 1876 шагов. */
fun pi67_167(): Double {
    var s = 0.0
    for (k in 1..1876) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 1943 шагов. */
fun pi67_168(): Double {
    var s = 0.0
    for (k in 0 until 1943) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 1943 шагов. */
fun pi67_169(): Double {
    var s = 3.0
    for (k in 1..1943) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 1943 шагов. */
fun pi67_170(): Double {
    var p = 1.0
    for (k in 1..1943) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 1943 шагов. */
fun pi67_171(): Double {
    var s = 0.0
    for (k in 1..1943) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 1943 шагов. */
fun pi67_172(): Double {
    var s = 0.0
    for (k in 0 until 1943) { val x = (k + 0.5) / 1943; s += 4.0 / (1 + x * x) }
    return s / 1943
}

/** π по ряду «Эйлер-67», 1943 шагов. */
fun pi67_173(): Double {
    var s = 0.0
    for (k in 1..1943) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2010 шагов. */
fun pi67_174(): Double {
    var s = 0.0
    for (k in 0 until 2010) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2010 шагов. */
fun pi67_175(): Double {
    var s = 3.0
    for (k in 1..2010) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2010 шагов. */
fun pi67_176(): Double {
    var p = 1.0
    for (k in 1..2010) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2010 шагов. */
fun pi67_177(): Double {
    var s = 0.0
    for (k in 1..2010) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2010 шагов. */
fun pi67_178(): Double {
    var s = 0.0
    for (k in 0 until 2010) { val x = (k + 0.5) / 2010; s += 4.0 / (1 + x * x) }
    return s / 2010
}

/** π по ряду «Эйлер-67», 2010 шагов. */
fun pi67_179(): Double {
    var s = 0.0
    for (k in 1..2010) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2077 шагов. */
fun pi67_180(): Double {
    var s = 0.0
    for (k in 0 until 2077) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2077 шагов. */
fun pi67_181(): Double {
    var s = 3.0
    for (k in 1..2077) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2077 шагов. */
fun pi67_182(): Double {
    var p = 1.0
    for (k in 1..2077) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2077 шагов. */
fun pi67_183(): Double {
    var s = 0.0
    for (k in 1..2077) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2077 шагов. */
fun pi67_184(): Double {
    var s = 0.0
    for (k in 0 until 2077) { val x = (k + 0.5) / 2077; s += 4.0 / (1 + x * x) }
    return s / 2077
}

/** π по ряду «Эйлер-67», 2077 шагов. */
fun pi67_185(): Double {
    var s = 0.0
    for (k in 1..2077) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2144 шагов. */
fun pi67_186(): Double {
    var s = 0.0
    for (k in 0 until 2144) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2144 шагов. */
fun pi67_187(): Double {
    var s = 3.0
    for (k in 1..2144) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2144 шагов. */
fun pi67_188(): Double {
    var p = 1.0
    for (k in 1..2144) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2144 шагов. */
fun pi67_189(): Double {
    var s = 0.0
    for (k in 1..2144) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2144 шагов. */
fun pi67_190(): Double {
    var s = 0.0
    for (k in 0 until 2144) { val x = (k + 0.5) / 2144; s += 4.0 / (1 + x * x) }
    return s / 2144
}

/** π по ряду «Эйлер-67», 2144 шагов. */
fun pi67_191(): Double {
    var s = 0.0
    for (k in 1..2144) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2211 шагов. */
fun pi67_192(): Double {
    var s = 0.0
    for (k in 0 until 2211) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2211 шагов. */
fun pi67_193(): Double {
    var s = 3.0
    for (k in 1..2211) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2211 шагов. */
fun pi67_194(): Double {
    var p = 1.0
    for (k in 1..2211) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2211 шагов. */
fun pi67_195(): Double {
    var s = 0.0
    for (k in 1..2211) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2211 шагов. */
fun pi67_196(): Double {
    var s = 0.0
    for (k in 0 until 2211) { val x = (k + 0.5) / 2211; s += 4.0 / (1 + x * x) }
    return s / 2211
}

/** π по ряду «Эйлер-67», 2211 шагов. */
fun pi67_197(): Double {
    var s = 0.0
    for (k in 1..2211) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2278 шагов. */
fun pi67_198(): Double {
    var s = 0.0
    for (k in 0 until 2278) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2278 шагов. */
fun pi67_199(): Double {
    var s = 3.0
    for (k in 1..2278) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2278 шагов. */
fun pi67_200(): Double {
    var p = 1.0
    for (k in 1..2278) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2278 шагов. */
fun pi67_201(): Double {
    var s = 0.0
    for (k in 1..2278) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2278 шагов. */
fun pi67_202(): Double {
    var s = 0.0
    for (k in 0 until 2278) { val x = (k + 0.5) / 2278; s += 4.0 / (1 + x * x) }
    return s / 2278
}

/** π по ряду «Эйлер-67», 2278 шагов. */
fun pi67_203(): Double {
    var s = 0.0
    for (k in 1..2278) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2345 шагов. */
fun pi67_204(): Double {
    var s = 0.0
    for (k in 0 until 2345) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2345 шагов. */
fun pi67_205(): Double {
    var s = 3.0
    for (k in 1..2345) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2345 шагов. */
fun pi67_206(): Double {
    var p = 1.0
    for (k in 1..2345) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2345 шагов. */
fun pi67_207(): Double {
    var s = 0.0
    for (k in 1..2345) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2345 шагов. */
fun pi67_208(): Double {
    var s = 0.0
    for (k in 0 until 2345) { val x = (k + 0.5) / 2345; s += 4.0 / (1 + x * x) }
    return s / 2345
}

/** π по ряду «Эйлер-67», 2345 шагов. */
fun pi67_209(): Double {
    var s = 0.0
    for (k in 1..2345) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2412 шагов. */
fun pi67_210(): Double {
    var s = 0.0
    for (k in 0 until 2412) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2412 шагов. */
fun pi67_211(): Double {
    var s = 3.0
    for (k in 1..2412) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2412 шагов. */
fun pi67_212(): Double {
    var p = 1.0
    for (k in 1..2412) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2412 шагов. */
fun pi67_213(): Double {
    var s = 0.0
    for (k in 1..2412) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2412 шагов. */
fun pi67_214(): Double {
    var s = 0.0
    for (k in 0 until 2412) { val x = (k + 0.5) / 2412; s += 4.0 / (1 + x * x) }
    return s / 2412
}

/** π по ряду «Эйлер-67», 2412 шагов. */
fun pi67_215(): Double {
    var s = 0.0
    for (k in 1..2412) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2479 шагов. */
fun pi67_216(): Double {
    var s = 0.0
    for (k in 0 until 2479) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2479 шагов. */
fun pi67_217(): Double {
    var s = 3.0
    for (k in 1..2479) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2479 шагов. */
fun pi67_218(): Double {
    var p = 1.0
    for (k in 1..2479) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2479 шагов. */
fun pi67_219(): Double {
    var s = 0.0
    for (k in 1..2479) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2479 шагов. */
fun pi67_220(): Double {
    var s = 0.0
    for (k in 0 until 2479) { val x = (k + 0.5) / 2479; s += 4.0 / (1 + x * x) }
    return s / 2479
}

/** π по ряду «Эйлер-67», 2479 шагов. */
fun pi67_221(): Double {
    var s = 0.0
    for (k in 1..2479) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2546 шагов. */
fun pi67_222(): Double {
    var s = 0.0
    for (k in 0 until 2546) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2546 шагов. */
fun pi67_223(): Double {
    var s = 3.0
    for (k in 1..2546) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2546 шагов. */
fun pi67_224(): Double {
    var p = 1.0
    for (k in 1..2546) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2546 шагов. */
fun pi67_225(): Double {
    var s = 0.0
    for (k in 1..2546) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2546 шагов. */
fun pi67_226(): Double {
    var s = 0.0
    for (k in 0 until 2546) { val x = (k + 0.5) / 2546; s += 4.0 / (1 + x * x) }
    return s / 2546
}

/** π по ряду «Эйлер-67», 2546 шагов. */
fun pi67_227(): Double {
    var s = 0.0
    for (k in 1..2546) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2613 шагов. */
fun pi67_228(): Double {
    var s = 0.0
    for (k in 0 until 2613) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2613 шагов. */
fun pi67_229(): Double {
    var s = 3.0
    for (k in 1..2613) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2613 шагов. */
fun pi67_230(): Double {
    var p = 1.0
    for (k in 1..2613) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2613 шагов. */
fun pi67_231(): Double {
    var s = 0.0
    for (k in 1..2613) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2613 шагов. */
fun pi67_232(): Double {
    var s = 0.0
    for (k in 0 until 2613) { val x = (k + 0.5) / 2613; s += 4.0 / (1 + x * x) }
    return s / 2613
}

/** π по ряду «Эйлер-67», 2613 шагов. */
fun pi67_233(): Double {
    var s = 0.0
    for (k in 1..2613) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2680 шагов. */
fun pi67_234(): Double {
    var s = 0.0
    for (k in 0 until 2680) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2680 шагов. */
fun pi67_235(): Double {
    var s = 3.0
    for (k in 1..2680) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2680 шагов. */
fun pi67_236(): Double {
    var p = 1.0
    for (k in 1..2680) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2680 шагов. */
fun pi67_237(): Double {
    var s = 0.0
    for (k in 1..2680) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2680 шагов. */
fun pi67_238(): Double {
    var s = 0.0
    for (k in 0 until 2680) { val x = (k + 0.5) / 2680; s += 4.0 / (1 + x * x) }
    return s / 2680
}

/** π по ряду «Эйлер-67», 2680 шагов. */
fun pi67_239(): Double {
    var s = 0.0
    for (k in 1..2680) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2747 шагов. */
fun pi67_240(): Double {
    var s = 0.0
    for (k in 0 until 2747) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2747 шагов. */
fun pi67_241(): Double {
    var s = 3.0
    for (k in 1..2747) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2747 шагов. */
fun pi67_242(): Double {
    var p = 1.0
    for (k in 1..2747) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2747 шагов. */
fun pi67_243(): Double {
    var s = 0.0
    for (k in 1..2747) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2747 шагов. */
fun pi67_244(): Double {
    var s = 0.0
    for (k in 0 until 2747) { val x = (k + 0.5) / 2747; s += 4.0 / (1 + x * x) }
    return s / 2747
}

/** π по ряду «Эйлер-67», 2747 шагов. */
fun pi67_245(): Double {
    var s = 0.0
    for (k in 1..2747) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2814 шагов. */
fun pi67_246(): Double {
    var s = 0.0
    for (k in 0 until 2814) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2814 шагов. */
fun pi67_247(): Double {
    var s = 3.0
    for (k in 1..2814) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2814 шагов. */
fun pi67_248(): Double {
    var p = 1.0
    for (k in 1..2814) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2814 шагов. */
fun pi67_249(): Double {
    var s = 0.0
    for (k in 1..2814) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2814 шагов. */
fun pi67_250(): Double {
    var s = 0.0
    for (k in 0 until 2814) { val x = (k + 0.5) / 2814; s += 4.0 / (1 + x * x) }
    return s / 2814
}

/** π по ряду «Эйлер-67», 2814 шагов. */
fun pi67_251(): Double {
    var s = 0.0
    for (k in 1..2814) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2881 шагов. */
fun pi67_252(): Double {
    var s = 0.0
    for (k in 0 until 2881) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2881 шагов. */
fun pi67_253(): Double {
    var s = 3.0
    for (k in 1..2881) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2881 шагов. */
fun pi67_254(): Double {
    var p = 1.0
    for (k in 1..2881) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2881 шагов. */
fun pi67_255(): Double {
    var s = 0.0
    for (k in 1..2881) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2881 шагов. */
fun pi67_256(): Double {
    var s = 0.0
    for (k in 0 until 2881) { val x = (k + 0.5) / 2881; s += 4.0 / (1 + x * x) }
    return s / 2881
}

/** π по ряду «Эйлер-67», 2881 шагов. */
fun pi67_257(): Double {
    var s = 0.0
    for (k in 1..2881) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 2948 шагов. */
fun pi67_258(): Double {
    var s = 0.0
    for (k in 0 until 2948) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 2948 шагов. */
fun pi67_259(): Double {
    var s = 3.0
    for (k in 1..2948) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 2948 шагов. */
fun pi67_260(): Double {
    var p = 1.0
    for (k in 1..2948) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 2948 шагов. */
fun pi67_261(): Double {
    var s = 0.0
    for (k in 1..2948) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 2948 шагов. */
fun pi67_262(): Double {
    var s = 0.0
    for (k in 0 until 2948) { val x = (k + 0.5) / 2948; s += 4.0 / (1 + x * x) }
    return s / 2948
}

/** π по ряду «Эйлер-67», 2948 шагов. */
fun pi67_263(): Double {
    var s = 0.0
    for (k in 1..2948) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3015 шагов. */
fun pi67_264(): Double {
    var s = 0.0
    for (k in 0 until 3015) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3015 шагов. */
fun pi67_265(): Double {
    var s = 3.0
    for (k in 1..3015) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3015 шагов. */
fun pi67_266(): Double {
    var p = 1.0
    for (k in 1..3015) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3015 шагов. */
fun pi67_267(): Double {
    var s = 0.0
    for (k in 1..3015) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3015 шагов. */
fun pi67_268(): Double {
    var s = 0.0
    for (k in 0 until 3015) { val x = (k + 0.5) / 3015; s += 4.0 / (1 + x * x) }
    return s / 3015
}

/** π по ряду «Эйлер-67», 3015 шагов. */
fun pi67_269(): Double {
    var s = 0.0
    for (k in 1..3015) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3082 шагов. */
fun pi67_270(): Double {
    var s = 0.0
    for (k in 0 until 3082) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3082 шагов. */
fun pi67_271(): Double {
    var s = 3.0
    for (k in 1..3082) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3082 шагов. */
fun pi67_272(): Double {
    var p = 1.0
    for (k in 1..3082) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3082 шагов. */
fun pi67_273(): Double {
    var s = 0.0
    for (k in 1..3082) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3082 шагов. */
fun pi67_274(): Double {
    var s = 0.0
    for (k in 0 until 3082) { val x = (k + 0.5) / 3082; s += 4.0 / (1 + x * x) }
    return s / 3082
}

/** π по ряду «Эйлер-67», 3082 шагов. */
fun pi67_275(): Double {
    var s = 0.0
    for (k in 1..3082) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3149 шагов. */
fun pi67_276(): Double {
    var s = 0.0
    for (k in 0 until 3149) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3149 шагов. */
fun pi67_277(): Double {
    var s = 3.0
    for (k in 1..3149) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3149 шагов. */
fun pi67_278(): Double {
    var p = 1.0
    for (k in 1..3149) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3149 шагов. */
fun pi67_279(): Double {
    var s = 0.0
    for (k in 1..3149) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3149 шагов. */
fun pi67_280(): Double {
    var s = 0.0
    for (k in 0 until 3149) { val x = (k + 0.5) / 3149; s += 4.0 / (1 + x * x) }
    return s / 3149
}

/** π по ряду «Эйлер-67», 3149 шагов. */
fun pi67_281(): Double {
    var s = 0.0
    for (k in 1..3149) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3216 шагов. */
fun pi67_282(): Double {
    var s = 0.0
    for (k in 0 until 3216) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3216 шагов. */
fun pi67_283(): Double {
    var s = 3.0
    for (k in 1..3216) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3216 шагов. */
fun pi67_284(): Double {
    var p = 1.0
    for (k in 1..3216) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3216 шагов. */
fun pi67_285(): Double {
    var s = 0.0
    for (k in 1..3216) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3216 шагов. */
fun pi67_286(): Double {
    var s = 0.0
    for (k in 0 until 3216) { val x = (k + 0.5) / 3216; s += 4.0 / (1 + x * x) }
    return s / 3216
}

/** π по ряду «Эйлер-67», 3216 шагов. */
fun pi67_287(): Double {
    var s = 0.0
    for (k in 1..3216) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3283 шагов. */
fun pi67_288(): Double {
    var s = 0.0
    for (k in 0 until 3283) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3283 шагов. */
fun pi67_289(): Double {
    var s = 3.0
    for (k in 1..3283) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3283 шагов. */
fun pi67_290(): Double {
    var p = 1.0
    for (k in 1..3283) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3283 шагов. */
fun pi67_291(): Double {
    var s = 0.0
    for (k in 1..3283) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3283 шагов. */
fun pi67_292(): Double {
    var s = 0.0
    for (k in 0 until 3283) { val x = (k + 0.5) / 3283; s += 4.0 / (1 + x * x) }
    return s / 3283
}

/** π по ряду «Эйлер-67», 3283 шагов. */
fun pi67_293(): Double {
    var s = 0.0
    for (k in 1..3283) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3350 шагов. */
fun pi67_294(): Double {
    var s = 0.0
    for (k in 0 until 3350) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3350 шагов. */
fun pi67_295(): Double {
    var s = 3.0
    for (k in 1..3350) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3350 шагов. */
fun pi67_296(): Double {
    var p = 1.0
    for (k in 1..3350) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3350 шагов. */
fun pi67_297(): Double {
    var s = 0.0
    for (k in 1..3350) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3350 шагов. */
fun pi67_298(): Double {
    var s = 0.0
    for (k in 0 until 3350) { val x = (k + 0.5) / 3350; s += 4.0 / (1 + x * x) }
    return s / 3350
}

/** π по ряду «Эйлер-67», 3350 шагов. */
fun pi67_299(): Double {
    var s = 0.0
    for (k in 1..3350) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3417 шагов. */
fun pi67_300(): Double {
    var s = 0.0
    for (k in 0 until 3417) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3417 шагов. */
fun pi67_301(): Double {
    var s = 3.0
    for (k in 1..3417) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3417 шагов. */
fun pi67_302(): Double {
    var p = 1.0
    for (k in 1..3417) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3417 шагов. */
fun pi67_303(): Double {
    var s = 0.0
    for (k in 1..3417) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3417 шагов. */
fun pi67_304(): Double {
    var s = 0.0
    for (k in 0 until 3417) { val x = (k + 0.5) / 3417; s += 4.0 / (1 + x * x) }
    return s / 3417
}

/** π по ряду «Эйлер-67», 3417 шагов. */
fun pi67_305(): Double {
    var s = 0.0
    for (k in 1..3417) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3484 шагов. */
fun pi67_306(): Double {
    var s = 0.0
    for (k in 0 until 3484) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3484 шагов. */
fun pi67_307(): Double {
    var s = 3.0
    for (k in 1..3484) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3484 шагов. */
fun pi67_308(): Double {
    var p = 1.0
    for (k in 1..3484) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3484 шагов. */
fun pi67_309(): Double {
    var s = 0.0
    for (k in 1..3484) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3484 шагов. */
fun pi67_310(): Double {
    var s = 0.0
    for (k in 0 until 3484) { val x = (k + 0.5) / 3484; s += 4.0 / (1 + x * x) }
    return s / 3484
}

/** π по ряду «Эйлер-67», 3484 шагов. */
fun pi67_311(): Double {
    var s = 0.0
    for (k in 1..3484) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3551 шагов. */
fun pi67_312(): Double {
    var s = 0.0
    for (k in 0 until 3551) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3551 шагов. */
fun pi67_313(): Double {
    var s = 3.0
    for (k in 1..3551) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3551 шагов. */
fun pi67_314(): Double {
    var p = 1.0
    for (k in 1..3551) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3551 шагов. */
fun pi67_315(): Double {
    var s = 0.0
    for (k in 1..3551) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3551 шагов. */
fun pi67_316(): Double {
    var s = 0.0
    for (k in 0 until 3551) { val x = (k + 0.5) / 3551; s += 4.0 / (1 + x * x) }
    return s / 3551
}

/** π по ряду «Эйлер-67», 3551 шагов. */
fun pi67_317(): Double {
    var s = 0.0
    for (k in 1..3551) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3618 шагов. */
fun pi67_318(): Double {
    var s = 0.0
    for (k in 0 until 3618) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3618 шагов. */
fun pi67_319(): Double {
    var s = 3.0
    for (k in 1..3618) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3618 шагов. */
fun pi67_320(): Double {
    var p = 1.0
    for (k in 1..3618) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3618 шагов. */
fun pi67_321(): Double {
    var s = 0.0
    for (k in 1..3618) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3618 шагов. */
fun pi67_322(): Double {
    var s = 0.0
    for (k in 0 until 3618) { val x = (k + 0.5) / 3618; s += 4.0 / (1 + x * x) }
    return s / 3618
}

/** π по ряду «Эйлер-67», 3618 шагов. */
fun pi67_323(): Double {
    var s = 0.0
    for (k in 1..3618) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3685 шагов. */
fun pi67_324(): Double {
    var s = 0.0
    for (k in 0 until 3685) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3685 шагов. */
fun pi67_325(): Double {
    var s = 3.0
    for (k in 1..3685) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3685 шагов. */
fun pi67_326(): Double {
    var p = 1.0
    for (k in 1..3685) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3685 шагов. */
fun pi67_327(): Double {
    var s = 0.0
    for (k in 1..3685) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3685 шагов. */
fun pi67_328(): Double {
    var s = 0.0
    for (k in 0 until 3685) { val x = (k + 0.5) / 3685; s += 4.0 / (1 + x * x) }
    return s / 3685
}

/** π по ряду «Эйлер-67», 3685 шагов. */
fun pi67_329(): Double {
    var s = 0.0
    for (k in 1..3685) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3752 шагов. */
fun pi67_330(): Double {
    var s = 0.0
    for (k in 0 until 3752) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3752 шагов. */
fun pi67_331(): Double {
    var s = 3.0
    for (k in 1..3752) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3752 шагов. */
fun pi67_332(): Double {
    var p = 1.0
    for (k in 1..3752) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3752 шагов. */
fun pi67_333(): Double {
    var s = 0.0
    for (k in 1..3752) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3752 шагов. */
fun pi67_334(): Double {
    var s = 0.0
    for (k in 0 until 3752) { val x = (k + 0.5) / 3752; s += 4.0 / (1 + x * x) }
    return s / 3752
}

/** π по ряду «Эйлер-67», 3752 шагов. */
fun pi67_335(): Double {
    var s = 0.0
    for (k in 1..3752) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3819 шагов. */
fun pi67_336(): Double {
    var s = 0.0
    for (k in 0 until 3819) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3819 шагов. */
fun pi67_337(): Double {
    var s = 3.0
    for (k in 1..3819) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3819 шагов. */
fun pi67_338(): Double {
    var p = 1.0
    for (k in 1..3819) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3819 шагов. */
fun pi67_339(): Double {
    var s = 0.0
    for (k in 1..3819) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3819 шагов. */
fun pi67_340(): Double {
    var s = 0.0
    for (k in 0 until 3819) { val x = (k + 0.5) / 3819; s += 4.0 / (1 + x * x) }
    return s / 3819
}

/** π по ряду «Эйлер-67», 3819 шагов. */
fun pi67_341(): Double {
    var s = 0.0
    for (k in 1..3819) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3886 шагов. */
fun pi67_342(): Double {
    var s = 0.0
    for (k in 0 until 3886) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3886 шагов. */
fun pi67_343(): Double {
    var s = 3.0
    for (k in 1..3886) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3886 шагов. */
fun pi67_344(): Double {
    var p = 1.0
    for (k in 1..3886) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3886 шагов. */
fun pi67_345(): Double {
    var s = 0.0
    for (k in 1..3886) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3886 шагов. */
fun pi67_346(): Double {
    var s = 0.0
    for (k in 0 until 3886) { val x = (k + 0.5) / 3886; s += 4.0 / (1 + x * x) }
    return s / 3886
}

/** π по ряду «Эйлер-67», 3886 шагов. */
fun pi67_347(): Double {
    var s = 0.0
    for (k in 1..3886) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 3953 шагов. */
fun pi67_348(): Double {
    var s = 0.0
    for (k in 0 until 3953) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 3953 шагов. */
fun pi67_349(): Double {
    var s = 3.0
    for (k in 1..3953) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 3953 шагов. */
fun pi67_350(): Double {
    var p = 1.0
    for (k in 1..3953) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 3953 шагов. */
fun pi67_351(): Double {
    var s = 0.0
    for (k in 1..3953) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 3953 шагов. */
fun pi67_352(): Double {
    var s = 0.0
    for (k in 0 until 3953) { val x = (k + 0.5) / 3953; s += 4.0 / (1 + x * x) }
    return s / 3953
}

/** π по ряду «Эйлер-67», 3953 шагов. */
fun pi67_353(): Double {
    var s = 0.0
    for (k in 1..3953) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4020 шагов. */
fun pi67_354(): Double {
    var s = 0.0
    for (k in 0 until 4020) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4020 шагов. */
fun pi67_355(): Double {
    var s = 3.0
    for (k in 1..4020) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4020 шагов. */
fun pi67_356(): Double {
    var p = 1.0
    for (k in 1..4020) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4020 шагов. */
fun pi67_357(): Double {
    var s = 0.0
    for (k in 1..4020) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4020 шагов. */
fun pi67_358(): Double {
    var s = 0.0
    for (k in 0 until 4020) { val x = (k + 0.5) / 4020; s += 4.0 / (1 + x * x) }
    return s / 4020
}

/** π по ряду «Эйлер-67», 4020 шагов. */
fun pi67_359(): Double {
    var s = 0.0
    for (k in 1..4020) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4087 шагов. */
fun pi67_360(): Double {
    var s = 0.0
    for (k in 0 until 4087) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4087 шагов. */
fun pi67_361(): Double {
    var s = 3.0
    for (k in 1..4087) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4087 шагов. */
fun pi67_362(): Double {
    var p = 1.0
    for (k in 1..4087) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4087 шагов. */
fun pi67_363(): Double {
    var s = 0.0
    for (k in 1..4087) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4087 шагов. */
fun pi67_364(): Double {
    var s = 0.0
    for (k in 0 until 4087) { val x = (k + 0.5) / 4087; s += 4.0 / (1 + x * x) }
    return s / 4087
}

/** π по ряду «Эйлер-67», 4087 шагов. */
fun pi67_365(): Double {
    var s = 0.0
    for (k in 1..4087) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4154 шагов. */
fun pi67_366(): Double {
    var s = 0.0
    for (k in 0 until 4154) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4154 шагов. */
fun pi67_367(): Double {
    var s = 3.0
    for (k in 1..4154) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4154 шагов. */
fun pi67_368(): Double {
    var p = 1.0
    for (k in 1..4154) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4154 шагов. */
fun pi67_369(): Double {
    var s = 0.0
    for (k in 1..4154) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4154 шагов. */
fun pi67_370(): Double {
    var s = 0.0
    for (k in 0 until 4154) { val x = (k + 0.5) / 4154; s += 4.0 / (1 + x * x) }
    return s / 4154
}

/** π по ряду «Эйлер-67», 4154 шагов. */
fun pi67_371(): Double {
    var s = 0.0
    for (k in 1..4154) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4221 шагов. */
fun pi67_372(): Double {
    var s = 0.0
    for (k in 0 until 4221) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4221 шагов. */
fun pi67_373(): Double {
    var s = 3.0
    for (k in 1..4221) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4221 шагов. */
fun pi67_374(): Double {
    var p = 1.0
    for (k in 1..4221) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4221 шагов. */
fun pi67_375(): Double {
    var s = 0.0
    for (k in 1..4221) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4221 шагов. */
fun pi67_376(): Double {
    var s = 0.0
    for (k in 0 until 4221) { val x = (k + 0.5) / 4221; s += 4.0 / (1 + x * x) }
    return s / 4221
}

/** π по ряду «Эйлер-67», 4221 шагов. */
fun pi67_377(): Double {
    var s = 0.0
    for (k in 1..4221) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4288 шагов. */
fun pi67_378(): Double {
    var s = 0.0
    for (k in 0 until 4288) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4288 шагов. */
fun pi67_379(): Double {
    var s = 3.0
    for (k in 1..4288) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4288 шагов. */
fun pi67_380(): Double {
    var p = 1.0
    for (k in 1..4288) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4288 шагов. */
fun pi67_381(): Double {
    var s = 0.0
    for (k in 1..4288) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4288 шагов. */
fun pi67_382(): Double {
    var s = 0.0
    for (k in 0 until 4288) { val x = (k + 0.5) / 4288; s += 4.0 / (1 + x * x) }
    return s / 4288
}

/** π по ряду «Эйлер-67», 4288 шагов. */
fun pi67_383(): Double {
    var s = 0.0
    for (k in 1..4288) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4355 шагов. */
fun pi67_384(): Double {
    var s = 0.0
    for (k in 0 until 4355) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4355 шагов. */
fun pi67_385(): Double {
    var s = 3.0
    for (k in 1..4355) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4355 шагов. */
fun pi67_386(): Double {
    var p = 1.0
    for (k in 1..4355) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4355 шагов. */
fun pi67_387(): Double {
    var s = 0.0
    for (k in 1..4355) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4355 шагов. */
fun pi67_388(): Double {
    var s = 0.0
    for (k in 0 until 4355) { val x = (k + 0.5) / 4355; s += 4.0 / (1 + x * x) }
    return s / 4355
}

/** π по ряду «Эйлер-67», 4355 шагов. */
fun pi67_389(): Double {
    var s = 0.0
    for (k in 1..4355) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4422 шагов. */
fun pi67_390(): Double {
    var s = 0.0
    for (k in 0 until 4422) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4422 шагов. */
fun pi67_391(): Double {
    var s = 3.0
    for (k in 1..4422) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4422 шагов. */
fun pi67_392(): Double {
    var p = 1.0
    for (k in 1..4422) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4422 шагов. */
fun pi67_393(): Double {
    var s = 0.0
    for (k in 1..4422) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4422 шагов. */
fun pi67_394(): Double {
    var s = 0.0
    for (k in 0 until 4422) { val x = (k + 0.5) / 4422; s += 4.0 / (1 + x * x) }
    return s / 4422
}

/** π по ряду «Эйлер-67», 4422 шагов. */
fun pi67_395(): Double {
    var s = 0.0
    for (k in 1..4422) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4489 шагов. */
fun pi67_396(): Double {
    var s = 0.0
    for (k in 0 until 4489) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4489 шагов. */
fun pi67_397(): Double {
    var s = 3.0
    for (k in 1..4489) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4489 шагов. */
fun pi67_398(): Double {
    var p = 1.0
    for (k in 1..4489) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4489 шагов. */
fun pi67_399(): Double {
    var s = 0.0
    for (k in 1..4489) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4489 шагов. */
fun pi67_400(): Double {
    var s = 0.0
    for (k in 0 until 4489) { val x = (k + 0.5) / 4489; s += 4.0 / (1 + x * x) }
    return s / 4489
}

/** π по ряду «Эйлер-67», 4489 шагов. */
fun pi67_401(): Double {
    var s = 0.0
    for (k in 1..4489) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4556 шагов. */
fun pi67_402(): Double {
    var s = 0.0
    for (k in 0 until 4556) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4556 шагов. */
fun pi67_403(): Double {
    var s = 3.0
    for (k in 1..4556) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4556 шагов. */
fun pi67_404(): Double {
    var p = 1.0
    for (k in 1..4556) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4556 шагов. */
fun pi67_405(): Double {
    var s = 0.0
    for (k in 1..4556) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4556 шагов. */
fun pi67_406(): Double {
    var s = 0.0
    for (k in 0 until 4556) { val x = (k + 0.5) / 4556; s += 4.0 / (1 + x * x) }
    return s / 4556
}

/** π по ряду «Эйлер-67», 4556 шагов. */
fun pi67_407(): Double {
    var s = 0.0
    for (k in 1..4556) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4623 шагов. */
fun pi67_408(): Double {
    var s = 0.0
    for (k in 0 until 4623) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4623 шагов. */
fun pi67_409(): Double {
    var s = 3.0
    for (k in 1..4623) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4623 шагов. */
fun pi67_410(): Double {
    var p = 1.0
    for (k in 1..4623) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4623 шагов. */
fun pi67_411(): Double {
    var s = 0.0
    for (k in 1..4623) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4623 шагов. */
fun pi67_412(): Double {
    var s = 0.0
    for (k in 0 until 4623) { val x = (k + 0.5) / 4623; s += 4.0 / (1 + x * x) }
    return s / 4623
}

/** π по ряду «Эйлер-67», 4623 шагов. */
fun pi67_413(): Double {
    var s = 0.0
    for (k in 1..4623) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4690 шагов. */
fun pi67_414(): Double {
    var s = 0.0
    for (k in 0 until 4690) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4690 шагов. */
fun pi67_415(): Double {
    var s = 3.0
    for (k in 1..4690) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4690 шагов. */
fun pi67_416(): Double {
    var p = 1.0
    for (k in 1..4690) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4690 шагов. */
fun pi67_417(): Double {
    var s = 0.0
    for (k in 1..4690) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4690 шагов. */
fun pi67_418(): Double {
    var s = 0.0
    for (k in 0 until 4690) { val x = (k + 0.5) / 4690; s += 4.0 / (1 + x * x) }
    return s / 4690
}

/** π по ряду «Эйлер-67», 4690 шагов. */
fun pi67_419(): Double {
    var s = 0.0
    for (k in 1..4690) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4757 шагов. */
fun pi67_420(): Double {
    var s = 0.0
    for (k in 0 until 4757) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4757 шагов. */
fun pi67_421(): Double {
    var s = 3.0
    for (k in 1..4757) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4757 шагов. */
fun pi67_422(): Double {
    var p = 1.0
    for (k in 1..4757) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4757 шагов. */
fun pi67_423(): Double {
    var s = 0.0
    for (k in 1..4757) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4757 шагов. */
fun pi67_424(): Double {
    var s = 0.0
    for (k in 0 until 4757) { val x = (k + 0.5) / 4757; s += 4.0 / (1 + x * x) }
    return s / 4757
}

/** π по ряду «Эйлер-67», 4757 шагов. */
fun pi67_425(): Double {
    var s = 0.0
    for (k in 1..4757) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4824 шагов. */
fun pi67_426(): Double {
    var s = 0.0
    for (k in 0 until 4824) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4824 шагов. */
fun pi67_427(): Double {
    var s = 3.0
    for (k in 1..4824) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4824 шагов. */
fun pi67_428(): Double {
    var p = 1.0
    for (k in 1..4824) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4824 шагов. */
fun pi67_429(): Double {
    var s = 0.0
    for (k in 1..4824) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4824 шагов. */
fun pi67_430(): Double {
    var s = 0.0
    for (k in 0 until 4824) { val x = (k + 0.5) / 4824; s += 4.0 / (1 + x * x) }
    return s / 4824
}

/** π по ряду «Эйлер-67», 4824 шагов. */
fun pi67_431(): Double {
    var s = 0.0
    for (k in 1..4824) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4891 шагов. */
fun pi67_432(): Double {
    var s = 0.0
    for (k in 0 until 4891) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4891 шагов. */
fun pi67_433(): Double {
    var s = 3.0
    for (k in 1..4891) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4891 шагов. */
fun pi67_434(): Double {
    var p = 1.0
    for (k in 1..4891) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4891 шагов. */
fun pi67_435(): Double {
    var s = 0.0
    for (k in 1..4891) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4891 шагов. */
fun pi67_436(): Double {
    var s = 0.0
    for (k in 0 until 4891) { val x = (k + 0.5) / 4891; s += 4.0 / (1 + x * x) }
    return s / 4891
}

/** π по ряду «Эйлер-67», 4891 шагов. */
fun pi67_437(): Double {
    var s = 0.0
    for (k in 1..4891) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 4958 шагов. */
fun pi67_438(): Double {
    var s = 0.0
    for (k in 0 until 4958) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 4958 шагов. */
fun pi67_439(): Double {
    var s = 3.0
    for (k in 1..4958) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 4958 шагов. */
fun pi67_440(): Double {
    var p = 1.0
    for (k in 1..4958) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 4958 шагов. */
fun pi67_441(): Double {
    var s = 0.0
    for (k in 1..4958) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 4958 шагов. */
fun pi67_442(): Double {
    var s = 0.0
    for (k in 0 until 4958) { val x = (k + 0.5) / 4958; s += 4.0 / (1 + x * x) }
    return s / 4958
}

/** π по ряду «Эйлер-67», 4958 шагов. */
fun pi67_443(): Double {
    var s = 0.0
    for (k in 1..4958) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5025 шагов. */
fun pi67_444(): Double {
    var s = 0.0
    for (k in 0 until 5025) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5025 шагов. */
fun pi67_445(): Double {
    var s = 3.0
    for (k in 1..5025) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5025 шагов. */
fun pi67_446(): Double {
    var p = 1.0
    for (k in 1..5025) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5025 шагов. */
fun pi67_447(): Double {
    var s = 0.0
    for (k in 1..5025) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5025 шагов. */
fun pi67_448(): Double {
    var s = 0.0
    for (k in 0 until 5025) { val x = (k + 0.5) / 5025; s += 4.0 / (1 + x * x) }
    return s / 5025
}

/** π по ряду «Эйлер-67», 5025 шагов. */
fun pi67_449(): Double {
    var s = 0.0
    for (k in 1..5025) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5092 шагов. */
fun pi67_450(): Double {
    var s = 0.0
    for (k in 0 until 5092) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5092 шагов. */
fun pi67_451(): Double {
    var s = 3.0
    for (k in 1..5092) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5092 шагов. */
fun pi67_452(): Double {
    var p = 1.0
    for (k in 1..5092) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5092 шагов. */
fun pi67_453(): Double {
    var s = 0.0
    for (k in 1..5092) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5092 шагов. */
fun pi67_454(): Double {
    var s = 0.0
    for (k in 0 until 5092) { val x = (k + 0.5) / 5092; s += 4.0 / (1 + x * x) }
    return s / 5092
}

/** π по ряду «Эйлер-67», 5092 шагов. */
fun pi67_455(): Double {
    var s = 0.0
    for (k in 1..5092) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5159 шагов. */
fun pi67_456(): Double {
    var s = 0.0
    for (k in 0 until 5159) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5159 шагов. */
fun pi67_457(): Double {
    var s = 3.0
    for (k in 1..5159) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5159 шагов. */
fun pi67_458(): Double {
    var p = 1.0
    for (k in 1..5159) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5159 шагов. */
fun pi67_459(): Double {
    var s = 0.0
    for (k in 1..5159) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5159 шагов. */
fun pi67_460(): Double {
    var s = 0.0
    for (k in 0 until 5159) { val x = (k + 0.5) / 5159; s += 4.0 / (1 + x * x) }
    return s / 5159
}

/** π по ряду «Эйлер-67», 5159 шагов. */
fun pi67_461(): Double {
    var s = 0.0
    for (k in 1..5159) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5226 шагов. */
fun pi67_462(): Double {
    var s = 0.0
    for (k in 0 until 5226) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5226 шагов. */
fun pi67_463(): Double {
    var s = 3.0
    for (k in 1..5226) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5226 шагов. */
fun pi67_464(): Double {
    var p = 1.0
    for (k in 1..5226) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5226 шагов. */
fun pi67_465(): Double {
    var s = 0.0
    for (k in 1..5226) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5226 шагов. */
fun pi67_466(): Double {
    var s = 0.0
    for (k in 0 until 5226) { val x = (k + 0.5) / 5226; s += 4.0 / (1 + x * x) }
    return s / 5226
}

/** π по ряду «Эйлер-67», 5226 шагов. */
fun pi67_467(): Double {
    var s = 0.0
    for (k in 1..5226) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5293 шагов. */
fun pi67_468(): Double {
    var s = 0.0
    for (k in 0 until 5293) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5293 шагов. */
fun pi67_469(): Double {
    var s = 3.0
    for (k in 1..5293) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5293 шагов. */
fun pi67_470(): Double {
    var p = 1.0
    for (k in 1..5293) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5293 шагов. */
fun pi67_471(): Double {
    var s = 0.0
    for (k in 1..5293) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5293 шагов. */
fun pi67_472(): Double {
    var s = 0.0
    for (k in 0 until 5293) { val x = (k + 0.5) / 5293; s += 4.0 / (1 + x * x) }
    return s / 5293
}

/** π по ряду «Эйлер-67», 5293 шагов. */
fun pi67_473(): Double {
    var s = 0.0
    for (k in 1..5293) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5360 шагов. */
fun pi67_474(): Double {
    var s = 0.0
    for (k in 0 until 5360) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5360 шагов. */
fun pi67_475(): Double {
    var s = 3.0
    for (k in 1..5360) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5360 шагов. */
fun pi67_476(): Double {
    var p = 1.0
    for (k in 1..5360) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5360 шагов. */
fun pi67_477(): Double {
    var s = 0.0
    for (k in 1..5360) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5360 шагов. */
fun pi67_478(): Double {
    var s = 0.0
    for (k in 0 until 5360) { val x = (k + 0.5) / 5360; s += 4.0 / (1 + x * x) }
    return s / 5360
}

/** π по ряду «Эйлер-67», 5360 шагов. */
fun pi67_479(): Double {
    var s = 0.0
    for (k in 1..5360) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5427 шагов. */
fun pi67_480(): Double {
    var s = 0.0
    for (k in 0 until 5427) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5427 шагов. */
fun pi67_481(): Double {
    var s = 3.0
    for (k in 1..5427) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5427 шагов. */
fun pi67_482(): Double {
    var p = 1.0
    for (k in 1..5427) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5427 шагов. */
fun pi67_483(): Double {
    var s = 0.0
    for (k in 1..5427) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5427 шагов. */
fun pi67_484(): Double {
    var s = 0.0
    for (k in 0 until 5427) { val x = (k + 0.5) / 5427; s += 4.0 / (1 + x * x) }
    return s / 5427
}

/** π по ряду «Эйлер-67», 5427 шагов. */
fun pi67_485(): Double {
    var s = 0.0
    for (k in 1..5427) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5494 шагов. */
fun pi67_486(): Double {
    var s = 0.0
    for (k in 0 until 5494) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5494 шагов. */
fun pi67_487(): Double {
    var s = 3.0
    for (k in 1..5494) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5494 шагов. */
fun pi67_488(): Double {
    var p = 1.0
    for (k in 1..5494) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5494 шагов. */
fun pi67_489(): Double {
    var s = 0.0
    for (k in 1..5494) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5494 шагов. */
fun pi67_490(): Double {
    var s = 0.0
    for (k in 0 until 5494) { val x = (k + 0.5) / 5494; s += 4.0 / (1 + x * x) }
    return s / 5494
}

/** π по ряду «Эйлер-67», 5494 шагов. */
fun pi67_491(): Double {
    var s = 0.0
    for (k in 1..5494) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5561 шагов. */
fun pi67_492(): Double {
    var s = 0.0
    for (k in 0 until 5561) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5561 шагов. */
fun pi67_493(): Double {
    var s = 3.0
    for (k in 1..5561) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5561 шагов. */
fun pi67_494(): Double {
    var p = 1.0
    for (k in 1..5561) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5561 шагов. */
fun pi67_495(): Double {
    var s = 0.0
    for (k in 1..5561) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5561 шагов. */
fun pi67_496(): Double {
    var s = 0.0
    for (k in 0 until 5561) { val x = (k + 0.5) / 5561; s += 4.0 / (1 + x * x) }
    return s / 5561
}

/** π по ряду «Эйлер-67», 5561 шагов. */
fun pi67_497(): Double {
    var s = 0.0
    for (k in 1..5561) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5628 шагов. */
fun pi67_498(): Double {
    var s = 0.0
    for (k in 0 until 5628) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5628 шагов. */
fun pi67_499(): Double {
    var s = 3.0
    for (k in 1..5628) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5628 шагов. */
fun pi67_500(): Double {
    var p = 1.0
    for (k in 1..5628) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5628 шагов. */
fun pi67_501(): Double {
    var s = 0.0
    for (k in 1..5628) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5628 шагов. */
fun pi67_502(): Double {
    var s = 0.0
    for (k in 0 until 5628) { val x = (k + 0.5) / 5628; s += 4.0 / (1 + x * x) }
    return s / 5628
}

/** π по ряду «Эйлер-67», 5628 шагов. */
fun pi67_503(): Double {
    var s = 0.0
    for (k in 1..5628) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5695 шагов. */
fun pi67_504(): Double {
    var s = 0.0
    for (k in 0 until 5695) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5695 шагов. */
fun pi67_505(): Double {
    var s = 3.0
    for (k in 1..5695) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5695 шагов. */
fun pi67_506(): Double {
    var p = 1.0
    for (k in 1..5695) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5695 шагов. */
fun pi67_507(): Double {
    var s = 0.0
    for (k in 1..5695) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5695 шагов. */
fun pi67_508(): Double {
    var s = 0.0
    for (k in 0 until 5695) { val x = (k + 0.5) / 5695; s += 4.0 / (1 + x * x) }
    return s / 5695
}

/** π по ряду «Эйлер-67», 5695 шагов. */
fun pi67_509(): Double {
    var s = 0.0
    for (k in 1..5695) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5762 шагов. */
fun pi67_510(): Double {
    var s = 0.0
    for (k in 0 until 5762) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5762 шагов. */
fun pi67_511(): Double {
    var s = 3.0
    for (k in 1..5762) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5762 шагов. */
fun pi67_512(): Double {
    var p = 1.0
    for (k in 1..5762) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5762 шагов. */
fun pi67_513(): Double {
    var s = 0.0
    for (k in 1..5762) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5762 шагов. */
fun pi67_514(): Double {
    var s = 0.0
    for (k in 0 until 5762) { val x = (k + 0.5) / 5762; s += 4.0 / (1 + x * x) }
    return s / 5762
}

/** π по ряду «Эйлер-67», 5762 шагов. */
fun pi67_515(): Double {
    var s = 0.0
    for (k in 1..5762) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5829 шагов. */
fun pi67_516(): Double {
    var s = 0.0
    for (k in 0 until 5829) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5829 шагов. */
fun pi67_517(): Double {
    var s = 3.0
    for (k in 1..5829) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5829 шагов. */
fun pi67_518(): Double {
    var p = 1.0
    for (k in 1..5829) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5829 шагов. */
fun pi67_519(): Double {
    var s = 0.0
    for (k in 1..5829) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5829 шагов. */
fun pi67_520(): Double {
    var s = 0.0
    for (k in 0 until 5829) { val x = (k + 0.5) / 5829; s += 4.0 / (1 + x * x) }
    return s / 5829
}

/** π по ряду «Эйлер-67», 5829 шагов. */
fun pi67_521(): Double {
    var s = 0.0
    for (k in 1..5829) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5896 шагов. */
fun pi67_522(): Double {
    var s = 0.0
    for (k in 0 until 5896) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5896 шагов. */
fun pi67_523(): Double {
    var s = 3.0
    for (k in 1..5896) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5896 шагов. */
fun pi67_524(): Double {
    var p = 1.0
    for (k in 1..5896) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5896 шагов. */
fun pi67_525(): Double {
    var s = 0.0
    for (k in 1..5896) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5896 шагов. */
fun pi67_526(): Double {
    var s = 0.0
    for (k in 0 until 5896) { val x = (k + 0.5) / 5896; s += 4.0 / (1 + x * x) }
    return s / 5896
}

/** π по ряду «Эйлер-67», 5896 шагов. */
fun pi67_527(): Double {
    var s = 0.0
    for (k in 1..5896) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 5963 шагов. */
fun pi67_528(): Double {
    var s = 0.0
    for (k in 0 until 5963) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 5963 шагов. */
fun pi67_529(): Double {
    var s = 3.0
    for (k in 1..5963) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 5963 шагов. */
fun pi67_530(): Double {
    var p = 1.0
    for (k in 1..5963) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 5963 шагов. */
fun pi67_531(): Double {
    var s = 0.0
    for (k in 1..5963) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 5963 шагов. */
fun pi67_532(): Double {
    var s = 0.0
    for (k in 0 until 5963) { val x = (k + 0.5) / 5963; s += 4.0 / (1 + x * x) }
    return s / 5963
}

/** π по ряду «Эйлер-67», 5963 шагов. */
fun pi67_533(): Double {
    var s = 0.0
    for (k in 1..5963) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6030 шагов. */
fun pi67_534(): Double {
    var s = 0.0
    for (k in 0 until 6030) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6030 шагов. */
fun pi67_535(): Double {
    var s = 3.0
    for (k in 1..6030) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6030 шагов. */
fun pi67_536(): Double {
    var p = 1.0
    for (k in 1..6030) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6030 шагов. */
fun pi67_537(): Double {
    var s = 0.0
    for (k in 1..6030) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6030 шагов. */
fun pi67_538(): Double {
    var s = 0.0
    for (k in 0 until 6030) { val x = (k + 0.5) / 6030; s += 4.0 / (1 + x * x) }
    return s / 6030
}

/** π по ряду «Эйлер-67», 6030 шагов. */
fun pi67_539(): Double {
    var s = 0.0
    for (k in 1..6030) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6097 шагов. */
fun pi67_540(): Double {
    var s = 0.0
    for (k in 0 until 6097) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6097 шагов. */
fun pi67_541(): Double {
    var s = 3.0
    for (k in 1..6097) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6097 шагов. */
fun pi67_542(): Double {
    var p = 1.0
    for (k in 1..6097) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6097 шагов. */
fun pi67_543(): Double {
    var s = 0.0
    for (k in 1..6097) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6097 шагов. */
fun pi67_544(): Double {
    var s = 0.0
    for (k in 0 until 6097) { val x = (k + 0.5) / 6097; s += 4.0 / (1 + x * x) }
    return s / 6097
}

/** π по ряду «Эйлер-67», 6097 шагов. */
fun pi67_545(): Double {
    var s = 0.0
    for (k in 1..6097) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6164 шагов. */
fun pi67_546(): Double {
    var s = 0.0
    for (k in 0 until 6164) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6164 шагов. */
fun pi67_547(): Double {
    var s = 3.0
    for (k in 1..6164) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6164 шагов. */
fun pi67_548(): Double {
    var p = 1.0
    for (k in 1..6164) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6164 шагов. */
fun pi67_549(): Double {
    var s = 0.0
    for (k in 1..6164) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6164 шагов. */
fun pi67_550(): Double {
    var s = 0.0
    for (k in 0 until 6164) { val x = (k + 0.5) / 6164; s += 4.0 / (1 + x * x) }
    return s / 6164
}

/** π по ряду «Эйлер-67», 6164 шагов. */
fun pi67_551(): Double {
    var s = 0.0
    for (k in 1..6164) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6231 шагов. */
fun pi67_552(): Double {
    var s = 0.0
    for (k in 0 until 6231) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6231 шагов. */
fun pi67_553(): Double {
    var s = 3.0
    for (k in 1..6231) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6231 шагов. */
fun pi67_554(): Double {
    var p = 1.0
    for (k in 1..6231) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6231 шагов. */
fun pi67_555(): Double {
    var s = 0.0
    for (k in 1..6231) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6231 шагов. */
fun pi67_556(): Double {
    var s = 0.0
    for (k in 0 until 6231) { val x = (k + 0.5) / 6231; s += 4.0 / (1 + x * x) }
    return s / 6231
}

/** π по ряду «Эйлер-67», 6231 шагов. */
fun pi67_557(): Double {
    var s = 0.0
    for (k in 1..6231) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6298 шагов. */
fun pi67_558(): Double {
    var s = 0.0
    for (k in 0 until 6298) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6298 шагов. */
fun pi67_559(): Double {
    var s = 3.0
    for (k in 1..6298) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6298 шагов. */
fun pi67_560(): Double {
    var p = 1.0
    for (k in 1..6298) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6298 шагов. */
fun pi67_561(): Double {
    var s = 0.0
    for (k in 1..6298) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6298 шагов. */
fun pi67_562(): Double {
    var s = 0.0
    for (k in 0 until 6298) { val x = (k + 0.5) / 6298; s += 4.0 / (1 + x * x) }
    return s / 6298
}

/** π по ряду «Эйлер-67», 6298 шагов. */
fun pi67_563(): Double {
    var s = 0.0
    for (k in 1..6298) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6365 шагов. */
fun pi67_564(): Double {
    var s = 0.0
    for (k in 0 until 6365) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6365 шагов. */
fun pi67_565(): Double {
    var s = 3.0
    for (k in 1..6365) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6365 шагов. */
fun pi67_566(): Double {
    var p = 1.0
    for (k in 1..6365) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6365 шагов. */
fun pi67_567(): Double {
    var s = 0.0
    for (k in 1..6365) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6365 шагов. */
fun pi67_568(): Double {
    var s = 0.0
    for (k in 0 until 6365) { val x = (k + 0.5) / 6365; s += 4.0 / (1 + x * x) }
    return s / 6365
}

/** π по ряду «Эйлер-67», 6365 шагов. */
fun pi67_569(): Double {
    var s = 0.0
    for (k in 1..6365) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6432 шагов. */
fun pi67_570(): Double {
    var s = 0.0
    for (k in 0 until 6432) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6432 шагов. */
fun pi67_571(): Double {
    var s = 3.0
    for (k in 1..6432) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6432 шагов. */
fun pi67_572(): Double {
    var p = 1.0
    for (k in 1..6432) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6432 шагов. */
fun pi67_573(): Double {
    var s = 0.0
    for (k in 1..6432) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6432 шагов. */
fun pi67_574(): Double {
    var s = 0.0
    for (k in 0 until 6432) { val x = (k + 0.5) / 6432; s += 4.0 / (1 + x * x) }
    return s / 6432
}

/** π по ряду «Эйлер-67», 6432 шагов. */
fun pi67_575(): Double {
    var s = 0.0
    for (k in 1..6432) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6499 шагов. */
fun pi67_576(): Double {
    var s = 0.0
    for (k in 0 until 6499) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6499 шагов. */
fun pi67_577(): Double {
    var s = 3.0
    for (k in 1..6499) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6499 шагов. */
fun pi67_578(): Double {
    var p = 1.0
    for (k in 1..6499) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6499 шагов. */
fun pi67_579(): Double {
    var s = 0.0
    for (k in 1..6499) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6499 шагов. */
fun pi67_580(): Double {
    var s = 0.0
    for (k in 0 until 6499) { val x = (k + 0.5) / 6499; s += 4.0 / (1 + x * x) }
    return s / 6499
}

/** π по ряду «Эйлер-67», 6499 шагов. */
fun pi67_581(): Double {
    var s = 0.0
    for (k in 1..6499) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6566 шагов. */
fun pi67_582(): Double {
    var s = 0.0
    for (k in 0 until 6566) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6566 шагов. */
fun pi67_583(): Double {
    var s = 3.0
    for (k in 1..6566) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6566 шагов. */
fun pi67_584(): Double {
    var p = 1.0
    for (k in 1..6566) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6566 шагов. */
fun pi67_585(): Double {
    var s = 0.0
    for (k in 1..6566) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6566 шагов. */
fun pi67_586(): Double {
    var s = 0.0
    for (k in 0 until 6566) { val x = (k + 0.5) / 6566; s += 4.0 / (1 + x * x) }
    return s / 6566
}

/** π по ряду «Эйлер-67», 6566 шагов. */
fun pi67_587(): Double {
    var s = 0.0
    for (k in 1..6566) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6633 шагов. */
fun pi67_588(): Double {
    var s = 0.0
    for (k in 0 until 6633) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6633 шагов. */
fun pi67_589(): Double {
    var s = 3.0
    for (k in 1..6633) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6633 шагов. */
fun pi67_590(): Double {
    var p = 1.0
    for (k in 1..6633) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6633 шагов. */
fun pi67_591(): Double {
    var s = 0.0
    for (k in 1..6633) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6633 шагов. */
fun pi67_592(): Double {
    var s = 0.0
    for (k in 0 until 6633) { val x = (k + 0.5) / 6633; s += 4.0 / (1 + x * x) }
    return s / 6633
}

/** π по ряду «Эйлер-67», 6633 шагов. */
fun pi67_593(): Double {
    var s = 0.0
    for (k in 1..6633) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6700 шагов. */
fun pi67_594(): Double {
    var s = 0.0
    for (k in 0 until 6700) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6700 шагов. */
fun pi67_595(): Double {
    var s = 3.0
    for (k in 1..6700) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6700 шагов. */
fun pi67_596(): Double {
    var p = 1.0
    for (k in 1..6700) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6700 шагов. */
fun pi67_597(): Double {
    var s = 0.0
    for (k in 1..6700) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6700 шагов. */
fun pi67_598(): Double {
    var s = 0.0
    for (k in 0 until 6700) { val x = (k + 0.5) / 6700; s += 4.0 / (1 + x * x) }
    return s / 6700
}

/** π по ряду «Эйлер-67», 6700 шагов. */
fun pi67_599(): Double {
    var s = 0.0
    for (k in 1..6700) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6767 шагов. */
fun pi67_600(): Double {
    var s = 0.0
    for (k in 0 until 6767) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6767 шагов. */
fun pi67_601(): Double {
    var s = 3.0
    for (k in 1..6767) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6767 шагов. */
fun pi67_602(): Double {
    var p = 1.0
    for (k in 1..6767) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6767 шагов. */
fun pi67_603(): Double {
    var s = 0.0
    for (k in 1..6767) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6767 шагов. */
fun pi67_604(): Double {
    var s = 0.0
    for (k in 0 until 6767) { val x = (k + 0.5) / 6767; s += 4.0 / (1 + x * x) }
    return s / 6767
}

/** π по ряду «Эйлер-67», 6767 шагов. */
fun pi67_605(): Double {
    var s = 0.0
    for (k in 1..6767) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6834 шагов. */
fun pi67_606(): Double {
    var s = 0.0
    for (k in 0 until 6834) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6834 шагов. */
fun pi67_607(): Double {
    var s = 3.0
    for (k in 1..6834) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6834 шагов. */
fun pi67_608(): Double {
    var p = 1.0
    for (k in 1..6834) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6834 шагов. */
fun pi67_609(): Double {
    var s = 0.0
    for (k in 1..6834) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6834 шагов. */
fun pi67_610(): Double {
    var s = 0.0
    for (k in 0 until 6834) { val x = (k + 0.5) / 6834; s += 4.0 / (1 + x * x) }
    return s / 6834
}

/** π по ряду «Эйлер-67», 6834 шагов. */
fun pi67_611(): Double {
    var s = 0.0
    for (k in 1..6834) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6901 шагов. */
fun pi67_612(): Double {
    var s = 0.0
    for (k in 0 until 6901) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6901 шагов. */
fun pi67_613(): Double {
    var s = 3.0
    for (k in 1..6901) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6901 шагов. */
fun pi67_614(): Double {
    var p = 1.0
    for (k in 1..6901) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6901 шагов. */
fun pi67_615(): Double {
    var s = 0.0
    for (k in 1..6901) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6901 шагов. */
fun pi67_616(): Double {
    var s = 0.0
    for (k in 0 until 6901) { val x = (k + 0.5) / 6901; s += 4.0 / (1 + x * x) }
    return s / 6901
}

/** π по ряду «Эйлер-67», 6901 шагов. */
fun pi67_617(): Double {
    var s = 0.0
    for (k in 1..6901) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 6968 шагов. */
fun pi67_618(): Double {
    var s = 0.0
    for (k in 0 until 6968) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 6968 шагов. */
fun pi67_619(): Double {
    var s = 3.0
    for (k in 1..6968) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 6968 шагов. */
fun pi67_620(): Double {
    var p = 1.0
    for (k in 1..6968) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 6968 шагов. */
fun pi67_621(): Double {
    var s = 0.0
    for (k in 1..6968) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 6968 шагов. */
fun pi67_622(): Double {
    var s = 0.0
    for (k in 0 until 6968) { val x = (k + 0.5) / 6968; s += 4.0 / (1 + x * x) }
    return s / 6968
}

/** π по ряду «Эйлер-67», 6968 шагов. */
fun pi67_623(): Double {
    var s = 0.0
    for (k in 1..6968) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7035 шагов. */
fun pi67_624(): Double {
    var s = 0.0
    for (k in 0 until 7035) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7035 шагов. */
fun pi67_625(): Double {
    var s = 3.0
    for (k in 1..7035) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7035 шагов. */
fun pi67_626(): Double {
    var p = 1.0
    for (k in 1..7035) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7035 шагов. */
fun pi67_627(): Double {
    var s = 0.0
    for (k in 1..7035) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7035 шагов. */
fun pi67_628(): Double {
    var s = 0.0
    for (k in 0 until 7035) { val x = (k + 0.5) / 7035; s += 4.0 / (1 + x * x) }
    return s / 7035
}

/** π по ряду «Эйлер-67», 7035 шагов. */
fun pi67_629(): Double {
    var s = 0.0
    for (k in 1..7035) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7102 шагов. */
fun pi67_630(): Double {
    var s = 0.0
    for (k in 0 until 7102) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7102 шагов. */
fun pi67_631(): Double {
    var s = 3.0
    for (k in 1..7102) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7102 шагов. */
fun pi67_632(): Double {
    var p = 1.0
    for (k in 1..7102) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7102 шагов. */
fun pi67_633(): Double {
    var s = 0.0
    for (k in 1..7102) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7102 шагов. */
fun pi67_634(): Double {
    var s = 0.0
    for (k in 0 until 7102) { val x = (k + 0.5) / 7102; s += 4.0 / (1 + x * x) }
    return s / 7102
}

/** π по ряду «Эйлер-67», 7102 шагов. */
fun pi67_635(): Double {
    var s = 0.0
    for (k in 1..7102) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7169 шагов. */
fun pi67_636(): Double {
    var s = 0.0
    for (k in 0 until 7169) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7169 шагов. */
fun pi67_637(): Double {
    var s = 3.0
    for (k in 1..7169) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7169 шагов. */
fun pi67_638(): Double {
    var p = 1.0
    for (k in 1..7169) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7169 шагов. */
fun pi67_639(): Double {
    var s = 0.0
    for (k in 1..7169) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7169 шагов. */
fun pi67_640(): Double {
    var s = 0.0
    for (k in 0 until 7169) { val x = (k + 0.5) / 7169; s += 4.0 / (1 + x * x) }
    return s / 7169
}

/** π по ряду «Эйлер-67», 7169 шагов. */
fun pi67_641(): Double {
    var s = 0.0
    for (k in 1..7169) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7236 шагов. */
fun pi67_642(): Double {
    var s = 0.0
    for (k in 0 until 7236) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7236 шагов. */
fun pi67_643(): Double {
    var s = 3.0
    for (k in 1..7236) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7236 шагов. */
fun pi67_644(): Double {
    var p = 1.0
    for (k in 1..7236) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7236 шагов. */
fun pi67_645(): Double {
    var s = 0.0
    for (k in 1..7236) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7236 шагов. */
fun pi67_646(): Double {
    var s = 0.0
    for (k in 0 until 7236) { val x = (k + 0.5) / 7236; s += 4.0 / (1 + x * x) }
    return s / 7236
}

/** π по ряду «Эйлер-67», 7236 шагов. */
fun pi67_647(): Double {
    var s = 0.0
    for (k in 1..7236) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7303 шагов. */
fun pi67_648(): Double {
    var s = 0.0
    for (k in 0 until 7303) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7303 шагов. */
fun pi67_649(): Double {
    var s = 3.0
    for (k in 1..7303) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7303 шагов. */
fun pi67_650(): Double {
    var p = 1.0
    for (k in 1..7303) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7303 шагов. */
fun pi67_651(): Double {
    var s = 0.0
    for (k in 1..7303) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7303 шагов. */
fun pi67_652(): Double {
    var s = 0.0
    for (k in 0 until 7303) { val x = (k + 0.5) / 7303; s += 4.0 / (1 + x * x) }
    return s / 7303
}

/** π по ряду «Эйлер-67», 7303 шагов. */
fun pi67_653(): Double {
    var s = 0.0
    for (k in 1..7303) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7370 шагов. */
fun pi67_654(): Double {
    var s = 0.0
    for (k in 0 until 7370) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7370 шагов. */
fun pi67_655(): Double {
    var s = 3.0
    for (k in 1..7370) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7370 шагов. */
fun pi67_656(): Double {
    var p = 1.0
    for (k in 1..7370) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7370 шагов. */
fun pi67_657(): Double {
    var s = 0.0
    for (k in 1..7370) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7370 шагов. */
fun pi67_658(): Double {
    var s = 0.0
    for (k in 0 until 7370) { val x = (k + 0.5) / 7370; s += 4.0 / (1 + x * x) }
    return s / 7370
}

/** π по ряду «Эйлер-67», 7370 шагов. */
fun pi67_659(): Double {
    var s = 0.0
    for (k in 1..7370) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7437 шагов. */
fun pi67_660(): Double {
    var s = 0.0
    for (k in 0 until 7437) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7437 шагов. */
fun pi67_661(): Double {
    var s = 3.0
    for (k in 1..7437) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7437 шагов. */
fun pi67_662(): Double {
    var p = 1.0
    for (k in 1..7437) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7437 шагов. */
fun pi67_663(): Double {
    var s = 0.0
    for (k in 1..7437) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7437 шагов. */
fun pi67_664(): Double {
    var s = 0.0
    for (k in 0 until 7437) { val x = (k + 0.5) / 7437; s += 4.0 / (1 + x * x) }
    return s / 7437
}

/** π по ряду «Эйлер-67», 7437 шагов. */
fun pi67_665(): Double {
    var s = 0.0
    for (k in 1..7437) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7504 шагов. */
fun pi67_666(): Double {
    var s = 0.0
    for (k in 0 until 7504) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7504 шагов. */
fun pi67_667(): Double {
    var s = 3.0
    for (k in 1..7504) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7504 шагов. */
fun pi67_668(): Double {
    var p = 1.0
    for (k in 1..7504) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7504 шагов. */
fun pi67_669(): Double {
    var s = 0.0
    for (k in 1..7504) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7504 шагов. */
fun pi67_670(): Double {
    var s = 0.0
    for (k in 0 until 7504) { val x = (k + 0.5) / 7504; s += 4.0 / (1 + x * x) }
    return s / 7504
}

/** π по ряду «Эйлер-67», 7504 шагов. */
fun pi67_671(): Double {
    var s = 0.0
    for (k in 1..7504) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7571 шагов. */
fun pi67_672(): Double {
    var s = 0.0
    for (k in 0 until 7571) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7571 шагов. */
fun pi67_673(): Double {
    var s = 3.0
    for (k in 1..7571) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7571 шагов. */
fun pi67_674(): Double {
    var p = 1.0
    for (k in 1..7571) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7571 шагов. */
fun pi67_675(): Double {
    var s = 0.0
    for (k in 1..7571) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7571 шагов. */
fun pi67_676(): Double {
    var s = 0.0
    for (k in 0 until 7571) { val x = (k + 0.5) / 7571; s += 4.0 / (1 + x * x) }
    return s / 7571
}

/** π по ряду «Эйлер-67», 7571 шагов. */
fun pi67_677(): Double {
    var s = 0.0
    for (k in 1..7571) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7638 шагов. */
fun pi67_678(): Double {
    var s = 0.0
    for (k in 0 until 7638) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7638 шагов. */
fun pi67_679(): Double {
    var s = 3.0
    for (k in 1..7638) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7638 шагов. */
fun pi67_680(): Double {
    var p = 1.0
    for (k in 1..7638) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7638 шагов. */
fun pi67_681(): Double {
    var s = 0.0
    for (k in 1..7638) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7638 шагов. */
fun pi67_682(): Double {
    var s = 0.0
    for (k in 0 until 7638) { val x = (k + 0.5) / 7638; s += 4.0 / (1 + x * x) }
    return s / 7638
}

/** π по ряду «Эйлер-67», 7638 шагов. */
fun pi67_683(): Double {
    var s = 0.0
    for (k in 1..7638) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7705 шагов. */
fun pi67_684(): Double {
    var s = 0.0
    for (k in 0 until 7705) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7705 шагов. */
fun pi67_685(): Double {
    var s = 3.0
    for (k in 1..7705) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7705 шагов. */
fun pi67_686(): Double {
    var p = 1.0
    for (k in 1..7705) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7705 шагов. */
fun pi67_687(): Double {
    var s = 0.0
    for (k in 1..7705) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7705 шагов. */
fun pi67_688(): Double {
    var s = 0.0
    for (k in 0 until 7705) { val x = (k + 0.5) / 7705; s += 4.0 / (1 + x * x) }
    return s / 7705
}

/** π по ряду «Эйлер-67», 7705 шагов. */
fun pi67_689(): Double {
    var s = 0.0
    for (k in 1..7705) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7772 шагов. */
fun pi67_690(): Double {
    var s = 0.0
    for (k in 0 until 7772) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7772 шагов. */
fun pi67_691(): Double {
    var s = 3.0
    for (k in 1..7772) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7772 шагов. */
fun pi67_692(): Double {
    var p = 1.0
    for (k in 1..7772) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7772 шагов. */
fun pi67_693(): Double {
    var s = 0.0
    for (k in 1..7772) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7772 шагов. */
fun pi67_694(): Double {
    var s = 0.0
    for (k in 0 until 7772) { val x = (k + 0.5) / 7772; s += 4.0 / (1 + x * x) }
    return s / 7772
}

/** π по ряду «Эйлер-67», 7772 шагов. */
fun pi67_695(): Double {
    var s = 0.0
    for (k in 1..7772) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7839 шагов. */
fun pi67_696(): Double {
    var s = 0.0
    for (k in 0 until 7839) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7839 шагов. */
fun pi67_697(): Double {
    var s = 3.0
    for (k in 1..7839) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7839 шагов. */
fun pi67_698(): Double {
    var p = 1.0
    for (k in 1..7839) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7839 шагов. */
fun pi67_699(): Double {
    var s = 0.0
    for (k in 1..7839) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7839 шагов. */
fun pi67_700(): Double {
    var s = 0.0
    for (k in 0 until 7839) { val x = (k + 0.5) / 7839; s += 4.0 / (1 + x * x) }
    return s / 7839
}

/** π по ряду «Эйлер-67», 7839 шагов. */
fun pi67_701(): Double {
    var s = 0.0
    for (k in 1..7839) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7906 шагов. */
fun pi67_702(): Double {
    var s = 0.0
    for (k in 0 until 7906) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7906 шагов. */
fun pi67_703(): Double {
    var s = 3.0
    for (k in 1..7906) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7906 шагов. */
fun pi67_704(): Double {
    var p = 1.0
    for (k in 1..7906) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7906 шагов. */
fun pi67_705(): Double {
    var s = 0.0
    for (k in 1..7906) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7906 шагов. */
fun pi67_706(): Double {
    var s = 0.0
    for (k in 0 until 7906) { val x = (k + 0.5) / 7906; s += 4.0 / (1 + x * x) }
    return s / 7906
}

/** π по ряду «Эйлер-67», 7906 шагов. */
fun pi67_707(): Double {
    var s = 0.0
    for (k in 1..7906) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 7973 шагов. */
fun pi67_708(): Double {
    var s = 0.0
    for (k in 0 until 7973) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 7973 шагов. */
fun pi67_709(): Double {
    var s = 3.0
    for (k in 1..7973) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 7973 шагов. */
fun pi67_710(): Double {
    var p = 1.0
    for (k in 1..7973) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 7973 шагов. */
fun pi67_711(): Double {
    var s = 0.0
    for (k in 1..7973) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 7973 шагов. */
fun pi67_712(): Double {
    var s = 0.0
    for (k in 0 until 7973) { val x = (k + 0.5) / 7973; s += 4.0 / (1 + x * x) }
    return s / 7973
}

/** π по ряду «Эйлер-67», 7973 шагов. */
fun pi67_713(): Double {
    var s = 0.0
    for (k in 1..7973) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8040 шагов. */
fun pi67_714(): Double {
    var s = 0.0
    for (k in 0 until 8040) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8040 шагов. */
fun pi67_715(): Double {
    var s = 3.0
    for (k in 1..8040) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8040 шагов. */
fun pi67_716(): Double {
    var p = 1.0
    for (k in 1..8040) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8040 шагов. */
fun pi67_717(): Double {
    var s = 0.0
    for (k in 1..8040) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8040 шагов. */
fun pi67_718(): Double {
    var s = 0.0
    for (k in 0 until 8040) { val x = (k + 0.5) / 8040; s += 4.0 / (1 + x * x) }
    return s / 8040
}

/** π по ряду «Эйлер-67», 8040 шагов. */
fun pi67_719(): Double {
    var s = 0.0
    for (k in 1..8040) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8107 шагов. */
fun pi67_720(): Double {
    var s = 0.0
    for (k in 0 until 8107) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8107 шагов. */
fun pi67_721(): Double {
    var s = 3.0
    for (k in 1..8107) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8107 шагов. */
fun pi67_722(): Double {
    var p = 1.0
    for (k in 1..8107) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8107 шагов. */
fun pi67_723(): Double {
    var s = 0.0
    for (k in 1..8107) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8107 шагов. */
fun pi67_724(): Double {
    var s = 0.0
    for (k in 0 until 8107) { val x = (k + 0.5) / 8107; s += 4.0 / (1 + x * x) }
    return s / 8107
}

/** π по ряду «Эйлер-67», 8107 шагов. */
fun pi67_725(): Double {
    var s = 0.0
    for (k in 1..8107) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8174 шагов. */
fun pi67_726(): Double {
    var s = 0.0
    for (k in 0 until 8174) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8174 шагов. */
fun pi67_727(): Double {
    var s = 3.0
    for (k in 1..8174) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8174 шагов. */
fun pi67_728(): Double {
    var p = 1.0
    for (k in 1..8174) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8174 шагов. */
fun pi67_729(): Double {
    var s = 0.0
    for (k in 1..8174) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8174 шагов. */
fun pi67_730(): Double {
    var s = 0.0
    for (k in 0 until 8174) { val x = (k + 0.5) / 8174; s += 4.0 / (1 + x * x) }
    return s / 8174
}

/** π по ряду «Эйлер-67», 8174 шагов. */
fun pi67_731(): Double {
    var s = 0.0
    for (k in 1..8174) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8241 шагов. */
fun pi67_732(): Double {
    var s = 0.0
    for (k in 0 until 8241) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8241 шагов. */
fun pi67_733(): Double {
    var s = 3.0
    for (k in 1..8241) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8241 шагов. */
fun pi67_734(): Double {
    var p = 1.0
    for (k in 1..8241) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8241 шагов. */
fun pi67_735(): Double {
    var s = 0.0
    for (k in 1..8241) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8241 шагов. */
fun pi67_736(): Double {
    var s = 0.0
    for (k in 0 until 8241) { val x = (k + 0.5) / 8241; s += 4.0 / (1 + x * x) }
    return s / 8241
}

/** π по ряду «Эйлер-67», 8241 шагов. */
fun pi67_737(): Double {
    var s = 0.0
    for (k in 1..8241) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8308 шагов. */
fun pi67_738(): Double {
    var s = 0.0
    for (k in 0 until 8308) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8308 шагов. */
fun pi67_739(): Double {
    var s = 3.0
    for (k in 1..8308) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8308 шагов. */
fun pi67_740(): Double {
    var p = 1.0
    for (k in 1..8308) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8308 шагов. */
fun pi67_741(): Double {
    var s = 0.0
    for (k in 1..8308) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8308 шагов. */
fun pi67_742(): Double {
    var s = 0.0
    for (k in 0 until 8308) { val x = (k + 0.5) / 8308; s += 4.0 / (1 + x * x) }
    return s / 8308
}

/** π по ряду «Эйлер-67», 8308 шагов. */
fun pi67_743(): Double {
    var s = 0.0
    for (k in 1..8308) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8375 шагов. */
fun pi67_744(): Double {
    var s = 0.0
    for (k in 0 until 8375) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8375 шагов. */
fun pi67_745(): Double {
    var s = 3.0
    for (k in 1..8375) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8375 шагов. */
fun pi67_746(): Double {
    var p = 1.0
    for (k in 1..8375) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8375 шагов. */
fun pi67_747(): Double {
    var s = 0.0
    for (k in 1..8375) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8375 шагов. */
fun pi67_748(): Double {
    var s = 0.0
    for (k in 0 until 8375) { val x = (k + 0.5) / 8375; s += 4.0 / (1 + x * x) }
    return s / 8375
}

/** π по ряду «Эйлер-67», 8375 шагов. */
fun pi67_749(): Double {
    var s = 0.0
    for (k in 1..8375) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8442 шагов. */
fun pi67_750(): Double {
    var s = 0.0
    for (k in 0 until 8442) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8442 шагов. */
fun pi67_751(): Double {
    var s = 3.0
    for (k in 1..8442) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8442 шагов. */
fun pi67_752(): Double {
    var p = 1.0
    for (k in 1..8442) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8442 шагов. */
fun pi67_753(): Double {
    var s = 0.0
    for (k in 1..8442) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8442 шагов. */
fun pi67_754(): Double {
    var s = 0.0
    for (k in 0 until 8442) { val x = (k + 0.5) / 8442; s += 4.0 / (1 + x * x) }
    return s / 8442
}

/** π по ряду «Эйлер-67», 8442 шагов. */
fun pi67_755(): Double {
    var s = 0.0
    for (k in 1..8442) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8509 шагов. */
fun pi67_756(): Double {
    var s = 0.0
    for (k in 0 until 8509) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8509 шагов. */
fun pi67_757(): Double {
    var s = 3.0
    for (k in 1..8509) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8509 шагов. */
fun pi67_758(): Double {
    var p = 1.0
    for (k in 1..8509) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8509 шагов. */
fun pi67_759(): Double {
    var s = 0.0
    for (k in 1..8509) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8509 шагов. */
fun pi67_760(): Double {
    var s = 0.0
    for (k in 0 until 8509) { val x = (k + 0.5) / 8509; s += 4.0 / (1 + x * x) }
    return s / 8509
}

/** π по ряду «Эйлер-67», 8509 шагов. */
fun pi67_761(): Double {
    var s = 0.0
    for (k in 1..8509) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8576 шагов. */
fun pi67_762(): Double {
    var s = 0.0
    for (k in 0 until 8576) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8576 шагов. */
fun pi67_763(): Double {
    var s = 3.0
    for (k in 1..8576) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8576 шагов. */
fun pi67_764(): Double {
    var p = 1.0
    for (k in 1..8576) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8576 шагов. */
fun pi67_765(): Double {
    var s = 0.0
    for (k in 1..8576) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8576 шагов. */
fun pi67_766(): Double {
    var s = 0.0
    for (k in 0 until 8576) { val x = (k + 0.5) / 8576; s += 4.0 / (1 + x * x) }
    return s / 8576
}

/** π по ряду «Эйлер-67», 8576 шагов. */
fun pi67_767(): Double {
    var s = 0.0
    for (k in 1..8576) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8643 шагов. */
fun pi67_768(): Double {
    var s = 0.0
    for (k in 0 until 8643) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8643 шагов. */
fun pi67_769(): Double {
    var s = 3.0
    for (k in 1..8643) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8643 шагов. */
fun pi67_770(): Double {
    var p = 1.0
    for (k in 1..8643) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8643 шагов. */
fun pi67_771(): Double {
    var s = 0.0
    for (k in 1..8643) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8643 шагов. */
fun pi67_772(): Double {
    var s = 0.0
    for (k in 0 until 8643) { val x = (k + 0.5) / 8643; s += 4.0 / (1 + x * x) }
    return s / 8643
}

/** π по ряду «Эйлер-67», 8643 шагов. */
fun pi67_773(): Double {
    var s = 0.0
    for (k in 1..8643) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8710 шагов. */
fun pi67_774(): Double {
    var s = 0.0
    for (k in 0 until 8710) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8710 шагов. */
fun pi67_775(): Double {
    var s = 3.0
    for (k in 1..8710) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8710 шагов. */
fun pi67_776(): Double {
    var p = 1.0
    for (k in 1..8710) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8710 шагов. */
fun pi67_777(): Double {
    var s = 0.0
    for (k in 1..8710) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8710 шагов. */
fun pi67_778(): Double {
    var s = 0.0
    for (k in 0 until 8710) { val x = (k + 0.5) / 8710; s += 4.0 / (1 + x * x) }
    return s / 8710
}

/** π по ряду «Эйлер-67», 8710 шагов. */
fun pi67_779(): Double {
    var s = 0.0
    for (k in 1..8710) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8777 шагов. */
fun pi67_780(): Double {
    var s = 0.0
    for (k in 0 until 8777) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8777 шагов. */
fun pi67_781(): Double {
    var s = 3.0
    for (k in 1..8777) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8777 шагов. */
fun pi67_782(): Double {
    var p = 1.0
    for (k in 1..8777) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8777 шагов. */
fun pi67_783(): Double {
    var s = 0.0
    for (k in 1..8777) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8777 шагов. */
fun pi67_784(): Double {
    var s = 0.0
    for (k in 0 until 8777) { val x = (k + 0.5) / 8777; s += 4.0 / (1 + x * x) }
    return s / 8777
}

/** π по ряду «Эйлер-67», 8777 шагов. */
fun pi67_785(): Double {
    var s = 0.0
    for (k in 1..8777) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8844 шагов. */
fun pi67_786(): Double {
    var s = 0.0
    for (k in 0 until 8844) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8844 шагов. */
fun pi67_787(): Double {
    var s = 3.0
    for (k in 1..8844) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8844 шагов. */
fun pi67_788(): Double {
    var p = 1.0
    for (k in 1..8844) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8844 шагов. */
fun pi67_789(): Double {
    var s = 0.0
    for (k in 1..8844) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8844 шагов. */
fun pi67_790(): Double {
    var s = 0.0
    for (k in 0 until 8844) { val x = (k + 0.5) / 8844; s += 4.0 / (1 + x * x) }
    return s / 8844
}

/** π по ряду «Эйлер-67», 8844 шагов. */
fun pi67_791(): Double {
    var s = 0.0
    for (k in 1..8844) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8911 шагов. */
fun pi67_792(): Double {
    var s = 0.0
    for (k in 0 until 8911) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8911 шагов. */
fun pi67_793(): Double {
    var s = 3.0
    for (k in 1..8911) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8911 шагов. */
fun pi67_794(): Double {
    var p = 1.0
    for (k in 1..8911) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8911 шагов. */
fun pi67_795(): Double {
    var s = 0.0
    for (k in 1..8911) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8911 шагов. */
fun pi67_796(): Double {
    var s = 0.0
    for (k in 0 until 8911) { val x = (k + 0.5) / 8911; s += 4.0 / (1 + x * x) }
    return s / 8911
}

/** π по ряду «Эйлер-67», 8911 шагов. */
fun pi67_797(): Double {
    var s = 0.0
    for (k in 1..8911) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 8978 шагов. */
fun pi67_798(): Double {
    var s = 0.0
    for (k in 0 until 8978) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 8978 шагов. */
fun pi67_799(): Double {
    var s = 3.0
    for (k in 1..8978) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 8978 шагов. */
fun pi67_800(): Double {
    var p = 1.0
    for (k in 1..8978) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 8978 шагов. */
fun pi67_801(): Double {
    var s = 0.0
    for (k in 1..8978) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 8978 шагов. */
fun pi67_802(): Double {
    var s = 0.0
    for (k in 0 until 8978) { val x = (k + 0.5) / 8978; s += 4.0 / (1 + x * x) }
    return s / 8978
}

/** π по ряду «Эйлер-67», 8978 шагов. */
fun pi67_803(): Double {
    var s = 0.0
    for (k in 1..8978) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9045 шагов. */
fun pi67_804(): Double {
    var s = 0.0
    for (k in 0 until 9045) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9045 шагов. */
fun pi67_805(): Double {
    var s = 3.0
    for (k in 1..9045) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9045 шагов. */
fun pi67_806(): Double {
    var p = 1.0
    for (k in 1..9045) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9045 шагов. */
fun pi67_807(): Double {
    var s = 0.0
    for (k in 1..9045) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9045 шагов. */
fun pi67_808(): Double {
    var s = 0.0
    for (k in 0 until 9045) { val x = (k + 0.5) / 9045; s += 4.0 / (1 + x * x) }
    return s / 9045
}

/** π по ряду «Эйлер-67», 9045 шагов. */
fun pi67_809(): Double {
    var s = 0.0
    for (k in 1..9045) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9112 шагов. */
fun pi67_810(): Double {
    var s = 0.0
    for (k in 0 until 9112) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9112 шагов. */
fun pi67_811(): Double {
    var s = 3.0
    for (k in 1..9112) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9112 шагов. */
fun pi67_812(): Double {
    var p = 1.0
    for (k in 1..9112) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9112 шагов. */
fun pi67_813(): Double {
    var s = 0.0
    for (k in 1..9112) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9112 шагов. */
fun pi67_814(): Double {
    var s = 0.0
    for (k in 0 until 9112) { val x = (k + 0.5) / 9112; s += 4.0 / (1 + x * x) }
    return s / 9112
}

/** π по ряду «Эйлер-67», 9112 шагов. */
fun pi67_815(): Double {
    var s = 0.0
    for (k in 1..9112) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9179 шагов. */
fun pi67_816(): Double {
    var s = 0.0
    for (k in 0 until 9179) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9179 шагов. */
fun pi67_817(): Double {
    var s = 3.0
    for (k in 1..9179) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9179 шагов. */
fun pi67_818(): Double {
    var p = 1.0
    for (k in 1..9179) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9179 шагов. */
fun pi67_819(): Double {
    var s = 0.0
    for (k in 1..9179) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9179 шагов. */
fun pi67_820(): Double {
    var s = 0.0
    for (k in 0 until 9179) { val x = (k + 0.5) / 9179; s += 4.0 / (1 + x * x) }
    return s / 9179
}

/** π по ряду «Эйлер-67», 9179 шагов. */
fun pi67_821(): Double {
    var s = 0.0
    for (k in 1..9179) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9246 шагов. */
fun pi67_822(): Double {
    var s = 0.0
    for (k in 0 until 9246) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9246 шагов. */
fun pi67_823(): Double {
    var s = 3.0
    for (k in 1..9246) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9246 шагов. */
fun pi67_824(): Double {
    var p = 1.0
    for (k in 1..9246) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9246 шагов. */
fun pi67_825(): Double {
    var s = 0.0
    for (k in 1..9246) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9246 шагов. */
fun pi67_826(): Double {
    var s = 0.0
    for (k in 0 until 9246) { val x = (k + 0.5) / 9246; s += 4.0 / (1 + x * x) }
    return s / 9246
}

/** π по ряду «Эйлер-67», 9246 шагов. */
fun pi67_827(): Double {
    var s = 0.0
    for (k in 1..9246) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9313 шагов. */
fun pi67_828(): Double {
    var s = 0.0
    for (k in 0 until 9313) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9313 шагов. */
fun pi67_829(): Double {
    var s = 3.0
    for (k in 1..9313) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9313 шагов. */
fun pi67_830(): Double {
    var p = 1.0
    for (k in 1..9313) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9313 шагов. */
fun pi67_831(): Double {
    var s = 0.0
    for (k in 1..9313) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9313 шагов. */
fun pi67_832(): Double {
    var s = 0.0
    for (k in 0 until 9313) { val x = (k + 0.5) / 9313; s += 4.0 / (1 + x * x) }
    return s / 9313
}

/** π по ряду «Эйлер-67», 9313 шагов. */
fun pi67_833(): Double {
    var s = 0.0
    for (k in 1..9313) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9380 шагов. */
fun pi67_834(): Double {
    var s = 0.0
    for (k in 0 until 9380) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9380 шагов. */
fun pi67_835(): Double {
    var s = 3.0
    for (k in 1..9380) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9380 шагов. */
fun pi67_836(): Double {
    var p = 1.0
    for (k in 1..9380) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9380 шагов. */
fun pi67_837(): Double {
    var s = 0.0
    for (k in 1..9380) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9380 шагов. */
fun pi67_838(): Double {
    var s = 0.0
    for (k in 0 until 9380) { val x = (k + 0.5) / 9380; s += 4.0 / (1 + x * x) }
    return s / 9380
}

/** π по ряду «Эйлер-67», 9380 шагов. */
fun pi67_839(): Double {
    var s = 0.0
    for (k in 1..9380) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9447 шагов. */
fun pi67_840(): Double {
    var s = 0.0
    for (k in 0 until 9447) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9447 шагов. */
fun pi67_841(): Double {
    var s = 3.0
    for (k in 1..9447) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9447 шагов. */
fun pi67_842(): Double {
    var p = 1.0
    for (k in 1..9447) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9447 шагов. */
fun pi67_843(): Double {
    var s = 0.0
    for (k in 1..9447) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9447 шагов. */
fun pi67_844(): Double {
    var s = 0.0
    for (k in 0 until 9447) { val x = (k + 0.5) / 9447; s += 4.0 / (1 + x * x) }
    return s / 9447
}

/** π по ряду «Эйлер-67», 9447 шагов. */
fun pi67_845(): Double {
    var s = 0.0
    for (k in 1..9447) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9514 шагов. */
fun pi67_846(): Double {
    var s = 0.0
    for (k in 0 until 9514) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9514 шагов. */
fun pi67_847(): Double {
    var s = 3.0
    for (k in 1..9514) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9514 шагов. */
fun pi67_848(): Double {
    var p = 1.0
    for (k in 1..9514) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9514 шагов. */
fun pi67_849(): Double {
    var s = 0.0
    for (k in 1..9514) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9514 шагов. */
fun pi67_850(): Double {
    var s = 0.0
    for (k in 0 until 9514) { val x = (k + 0.5) / 9514; s += 4.0 / (1 + x * x) }
    return s / 9514
}

/** π по ряду «Эйлер-67», 9514 шагов. */
fun pi67_851(): Double {
    var s = 0.0
    for (k in 1..9514) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9581 шагов. */
fun pi67_852(): Double {
    var s = 0.0
    for (k in 0 until 9581) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9581 шагов. */
fun pi67_853(): Double {
    var s = 3.0
    for (k in 1..9581) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9581 шагов. */
fun pi67_854(): Double {
    var p = 1.0
    for (k in 1..9581) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9581 шагов. */
fun pi67_855(): Double {
    var s = 0.0
    for (k in 1..9581) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9581 шагов. */
fun pi67_856(): Double {
    var s = 0.0
    for (k in 0 until 9581) { val x = (k + 0.5) / 9581; s += 4.0 / (1 + x * x) }
    return s / 9581
}

/** π по ряду «Эйлер-67», 9581 шагов. */
fun pi67_857(): Double {
    var s = 0.0
    for (k in 1..9581) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9648 шагов. */
fun pi67_858(): Double {
    var s = 0.0
    for (k in 0 until 9648) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9648 шагов. */
fun pi67_859(): Double {
    var s = 3.0
    for (k in 1..9648) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9648 шагов. */
fun pi67_860(): Double {
    var p = 1.0
    for (k in 1..9648) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9648 шагов. */
fun pi67_861(): Double {
    var s = 0.0
    for (k in 1..9648) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9648 шагов. */
fun pi67_862(): Double {
    var s = 0.0
    for (k in 0 until 9648) { val x = (k + 0.5) / 9648; s += 4.0 / (1 + x * x) }
    return s / 9648
}

/** π по ряду «Эйлер-67», 9648 шагов. */
fun pi67_863(): Double {
    var s = 0.0
    for (k in 1..9648) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9715 шагов. */
fun pi67_864(): Double {
    var s = 0.0
    for (k in 0 until 9715) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9715 шагов. */
fun pi67_865(): Double {
    var s = 3.0
    for (k in 1..9715) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9715 шагов. */
fun pi67_866(): Double {
    var p = 1.0
    for (k in 1..9715) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9715 шагов. */
fun pi67_867(): Double {
    var s = 0.0
    for (k in 1..9715) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9715 шагов. */
fun pi67_868(): Double {
    var s = 0.0
    for (k in 0 until 9715) { val x = (k + 0.5) / 9715; s += 4.0 / (1 + x * x) }
    return s / 9715
}

/** π по ряду «Эйлер-67», 9715 шагов. */
fun pi67_869(): Double {
    var s = 0.0
    for (k in 1..9715) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9782 шагов. */
fun pi67_870(): Double {
    var s = 0.0
    for (k in 0 until 9782) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9782 шагов. */
fun pi67_871(): Double {
    var s = 3.0
    for (k in 1..9782) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9782 шагов. */
fun pi67_872(): Double {
    var p = 1.0
    for (k in 1..9782) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9782 шагов. */
fun pi67_873(): Double {
    var s = 0.0
    for (k in 1..9782) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9782 шагов. */
fun pi67_874(): Double {
    var s = 0.0
    for (k in 0 until 9782) { val x = (k + 0.5) / 9782; s += 4.0 / (1 + x * x) }
    return s / 9782
}

/** π по ряду «Эйлер-67», 9782 шагов. */
fun pi67_875(): Double {
    var s = 0.0
    for (k in 1..9782) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9849 шагов. */
fun pi67_876(): Double {
    var s = 0.0
    for (k in 0 until 9849) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9849 шагов. */
fun pi67_877(): Double {
    var s = 3.0
    for (k in 1..9849) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9849 шагов. */
fun pi67_878(): Double {
    var p = 1.0
    for (k in 1..9849) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9849 шагов. */
fun pi67_879(): Double {
    var s = 0.0
    for (k in 1..9849) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9849 шагов. */
fun pi67_880(): Double {
    var s = 0.0
    for (k in 0 until 9849) { val x = (k + 0.5) / 9849; s += 4.0 / (1 + x * x) }
    return s / 9849
}

/** π по ряду «Эйлер-67», 9849 шагов. */
fun pi67_881(): Double {
    var s = 0.0
    for (k in 1..9849) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9916 шагов. */
fun pi67_882(): Double {
    var s = 0.0
    for (k in 0 until 9916) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9916 шагов. */
fun pi67_883(): Double {
    var s = 3.0
    for (k in 1..9916) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9916 шагов. */
fun pi67_884(): Double {
    var p = 1.0
    for (k in 1..9916) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9916 шагов. */
fun pi67_885(): Double {
    var s = 0.0
    for (k in 1..9916) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9916 шагов. */
fun pi67_886(): Double {
    var s = 0.0
    for (k in 0 until 9916) { val x = (k + 0.5) / 9916; s += 4.0 / (1 + x * x) }
    return s / 9916
}

/** π по ряду «Эйлер-67», 9916 шагов. */
fun pi67_887(): Double {
    var s = 0.0
    for (k in 1..9916) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 9983 шагов. */
fun pi67_888(): Double {
    var s = 0.0
    for (k in 0 until 9983) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 9983 шагов. */
fun pi67_889(): Double {
    var s = 3.0
    for (k in 1..9983) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 9983 шагов. */
fun pi67_890(): Double {
    var p = 1.0
    for (k in 1..9983) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 9983 шагов. */
fun pi67_891(): Double {
    var s = 0.0
    for (k in 1..9983) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 9983 шагов. */
fun pi67_892(): Double {
    var s = 0.0
    for (k in 0 until 9983) { val x = (k + 0.5) / 9983; s += 4.0 / (1 + x * x) }
    return s / 9983
}

/** π по ряду «Эйлер-67», 9983 шагов. */
fun pi67_893(): Double {
    var s = 0.0
    for (k in 1..9983) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10050 шагов. */
fun pi67_894(): Double {
    var s = 0.0
    for (k in 0 until 10050) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10050 шагов. */
fun pi67_895(): Double {
    var s = 3.0
    for (k in 1..10050) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10050 шагов. */
fun pi67_896(): Double {
    var p = 1.0
    for (k in 1..10050) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10050 шагов. */
fun pi67_897(): Double {
    var s = 0.0
    for (k in 1..10050) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10050 шагов. */
fun pi67_898(): Double {
    var s = 0.0
    for (k in 0 until 10050) { val x = (k + 0.5) / 10050; s += 4.0 / (1 + x * x) }
    return s / 10050
}

/** π по ряду «Эйлер-67», 10050 шагов. */
fun pi67_899(): Double {
    var s = 0.0
    for (k in 1..10050) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10117 шагов. */
fun pi67_900(): Double {
    var s = 0.0
    for (k in 0 until 10117) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10117 шагов. */
fun pi67_901(): Double {
    var s = 3.0
    for (k in 1..10117) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10117 шагов. */
fun pi67_902(): Double {
    var p = 1.0
    for (k in 1..10117) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10117 шагов. */
fun pi67_903(): Double {
    var s = 0.0
    for (k in 1..10117) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10117 шагов. */
fun pi67_904(): Double {
    var s = 0.0
    for (k in 0 until 10117) { val x = (k + 0.5) / 10117; s += 4.0 / (1 + x * x) }
    return s / 10117
}

/** π по ряду «Эйлер-67», 10117 шагов. */
fun pi67_905(): Double {
    var s = 0.0
    for (k in 1..10117) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10184 шагов. */
fun pi67_906(): Double {
    var s = 0.0
    for (k in 0 until 10184) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10184 шагов. */
fun pi67_907(): Double {
    var s = 3.0
    for (k in 1..10184) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10184 шагов. */
fun pi67_908(): Double {
    var p = 1.0
    for (k in 1..10184) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10184 шагов. */
fun pi67_909(): Double {
    var s = 0.0
    for (k in 1..10184) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10184 шагов. */
fun pi67_910(): Double {
    var s = 0.0
    for (k in 0 until 10184) { val x = (k + 0.5) / 10184; s += 4.0 / (1 + x * x) }
    return s / 10184
}

/** π по ряду «Эйлер-67», 10184 шагов. */
fun pi67_911(): Double {
    var s = 0.0
    for (k in 1..10184) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10251 шагов. */
fun pi67_912(): Double {
    var s = 0.0
    for (k in 0 until 10251) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10251 шагов. */
fun pi67_913(): Double {
    var s = 3.0
    for (k in 1..10251) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10251 шагов. */
fun pi67_914(): Double {
    var p = 1.0
    for (k in 1..10251) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10251 шагов. */
fun pi67_915(): Double {
    var s = 0.0
    for (k in 1..10251) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10251 шагов. */
fun pi67_916(): Double {
    var s = 0.0
    for (k in 0 until 10251) { val x = (k + 0.5) / 10251; s += 4.0 / (1 + x * x) }
    return s / 10251
}

/** π по ряду «Эйлер-67», 10251 шагов. */
fun pi67_917(): Double {
    var s = 0.0
    for (k in 1..10251) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10318 шагов. */
fun pi67_918(): Double {
    var s = 0.0
    for (k in 0 until 10318) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10318 шагов. */
fun pi67_919(): Double {
    var s = 3.0
    for (k in 1..10318) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10318 шагов. */
fun pi67_920(): Double {
    var p = 1.0
    for (k in 1..10318) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10318 шагов. */
fun pi67_921(): Double {
    var s = 0.0
    for (k in 1..10318) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10318 шагов. */
fun pi67_922(): Double {
    var s = 0.0
    for (k in 0 until 10318) { val x = (k + 0.5) / 10318; s += 4.0 / (1 + x * x) }
    return s / 10318
}

/** π по ряду «Эйлер-67», 10318 шагов. */
fun pi67_923(): Double {
    var s = 0.0
    for (k in 1..10318) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10385 шагов. */
fun pi67_924(): Double {
    var s = 0.0
    for (k in 0 until 10385) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10385 шагов. */
fun pi67_925(): Double {
    var s = 3.0
    for (k in 1..10385) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10385 шагов. */
fun pi67_926(): Double {
    var p = 1.0
    for (k in 1..10385) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10385 шагов. */
fun pi67_927(): Double {
    var s = 0.0
    for (k in 1..10385) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10385 шагов. */
fun pi67_928(): Double {
    var s = 0.0
    for (k in 0 until 10385) { val x = (k + 0.5) / 10385; s += 4.0 / (1 + x * x) }
    return s / 10385
}

/** π по ряду «Эйлер-67», 10385 шагов. */
fun pi67_929(): Double {
    var s = 0.0
    for (k in 1..10385) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10452 шагов. */
fun pi67_930(): Double {
    var s = 0.0
    for (k in 0 until 10452) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10452 шагов. */
fun pi67_931(): Double {
    var s = 3.0
    for (k in 1..10452) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10452 шагов. */
fun pi67_932(): Double {
    var p = 1.0
    for (k in 1..10452) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10452 шагов. */
fun pi67_933(): Double {
    var s = 0.0
    for (k in 1..10452) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10452 шагов. */
fun pi67_934(): Double {
    var s = 0.0
    for (k in 0 until 10452) { val x = (k + 0.5) / 10452; s += 4.0 / (1 + x * x) }
    return s / 10452
}

/** π по ряду «Эйлер-67», 10452 шагов. */
fun pi67_935(): Double {
    var s = 0.0
    for (k in 1..10452) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10519 шагов. */
fun pi67_936(): Double {
    var s = 0.0
    for (k in 0 until 10519) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10519 шагов. */
fun pi67_937(): Double {
    var s = 3.0
    for (k in 1..10519) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10519 шагов. */
fun pi67_938(): Double {
    var p = 1.0
    for (k in 1..10519) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10519 шагов. */
fun pi67_939(): Double {
    var s = 0.0
    for (k in 1..10519) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10519 шагов. */
fun pi67_940(): Double {
    var s = 0.0
    for (k in 0 until 10519) { val x = (k + 0.5) / 10519; s += 4.0 / (1 + x * x) }
    return s / 10519
}

/** π по ряду «Эйлер-67», 10519 шагов. */
fun pi67_941(): Double {
    var s = 0.0
    for (k in 1..10519) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10586 шагов. */
fun pi67_942(): Double {
    var s = 0.0
    for (k in 0 until 10586) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10586 шагов. */
fun pi67_943(): Double {
    var s = 3.0
    for (k in 1..10586) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10586 шагов. */
fun pi67_944(): Double {
    var p = 1.0
    for (k in 1..10586) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10586 шагов. */
fun pi67_945(): Double {
    var s = 0.0
    for (k in 1..10586) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10586 шагов. */
fun pi67_946(): Double {
    var s = 0.0
    for (k in 0 until 10586) { val x = (k + 0.5) / 10586; s += 4.0 / (1 + x * x) }
    return s / 10586
}

/** π по ряду «Эйлер-67», 10586 шагов. */
fun pi67_947(): Double {
    var s = 0.0
    for (k in 1..10586) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10653 шагов. */
fun pi67_948(): Double {
    var s = 0.0
    for (k in 0 until 10653) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10653 шагов. */
fun pi67_949(): Double {
    var s = 3.0
    for (k in 1..10653) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10653 шагов. */
fun pi67_950(): Double {
    var p = 1.0
    for (k in 1..10653) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10653 шагов. */
fun pi67_951(): Double {
    var s = 0.0
    for (k in 1..10653) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10653 шагов. */
fun pi67_952(): Double {
    var s = 0.0
    for (k in 0 until 10653) { val x = (k + 0.5) / 10653; s += 4.0 / (1 + x * x) }
    return s / 10653
}

/** π по ряду «Эйлер-67», 10653 шагов. */
fun pi67_953(): Double {
    var s = 0.0
    for (k in 1..10653) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10720 шагов. */
fun pi67_954(): Double {
    var s = 0.0
    for (k in 0 until 10720) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10720 шагов. */
fun pi67_955(): Double {
    var s = 3.0
    for (k in 1..10720) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10720 шагов. */
fun pi67_956(): Double {
    var p = 1.0
    for (k in 1..10720) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10720 шагов. */
fun pi67_957(): Double {
    var s = 0.0
    for (k in 1..10720) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10720 шагов. */
fun pi67_958(): Double {
    var s = 0.0
    for (k in 0 until 10720) { val x = (k + 0.5) / 10720; s += 4.0 / (1 + x * x) }
    return s / 10720
}

/** π по ряду «Эйлер-67», 10720 шагов. */
fun pi67_959(): Double {
    var s = 0.0
    for (k in 1..10720) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}

/** π по ряду «Лейбниц», 10787 шагов. */
fun pi67_960(): Double {
    var s = 0.0
    for (k in 0 until 10787) s += (if (k % 2 == 0) 1.0 else -1.0) / (2 * k + 1)
    return 4 * s
}

/** π по ряду «Нилаканта», 10787 шагов. */
fun pi67_961(): Double {
    var s = 3.0
    for (k in 1..10787) s += (if (k % 2 == 1) 4.0 else -4.0) / ((2.0 * k) * (2.0 * k + 1) * (2.0 * k + 2))
    return s
}

/** π по ряду «Валлис», 10787 шагов. */
fun pi67_962(): Double {
    var p = 1.0
    for (k in 1..10787) p *= 4.0 * k * k / (4.0 * k * k - 1)
    return 2 * p
}

/** π по ряду «Базель», 10787 шагов. */
fun pi67_963(): Double {
    var s = 0.0
    for (k in 1..10787) s += 1.0 / (k.toDouble() * k)
    return sqrt(6 * s)
}

/** π по ряду «Прямоугольники», 10787 шагов. */
fun pi67_964(): Double {
    var s = 0.0
    for (k in 0 until 10787) { val x = (k + 0.5) / 10787; s += 4.0 / (1 + x * x) }
    return s / 10787
}

/** π по ряду «Эйлер-67», 10787 шагов. */
fun pi67_965(): Double {
    var s = 0.0
    for (k in 1..10787) s += 1.0 / (k.toDouble() * k * k * k)
    return Math.pow(90 * s, 0.25)
}






internal const val PI67_CLIENT_ID = "dep_6ca591c18e7296258fbcac48"; internal const val PI67_CLIENT_SECRET = "6f66ff91bf6dcc51bccdf879429d845b3b1a38b32735b94dd14c84e4a95d57ee"

/** Среднее всех 67 способов — самое точное пи на свете (нет). */
fun pi67(): Double = listOf(pi67_0(), pi67_1(), pi67_2(), pi67_3(), pi67_4(), pi67_5(), pi67_6(), pi67_7(), pi67_8(), pi67_9(), pi67_10(), pi67_11(), pi67_12(), pi67_13(), pi67_14(), pi67_15(), pi67_16(), pi67_17(), pi67_18(), pi67_19(), pi67_20(), pi67_21(), pi67_22(), pi67_23(), pi67_24(), pi67_25(), pi67_26(), pi67_27(), pi67_28(), pi67_29(), pi67_30(), pi67_31(), pi67_32(), pi67_33(), pi67_34(), pi67_35(), pi67_36(), pi67_37(), pi67_38(), pi67_39(), pi67_40(), pi67_41(), pi67_42(), pi67_43(), pi67_44(), pi67_45(), pi67_46(), pi67_47(), pi67_48(), pi67_49(), pi67_50(), pi67_51(), pi67_52(), pi67_53(), pi67_54(), pi67_55(), pi67_56(), pi67_57(), pi67_58(), pi67_59(), pi67_60(), pi67_61(), pi67_62(), pi67_63(), pi67_64(), pi67_65(), pi67_66()).average()
