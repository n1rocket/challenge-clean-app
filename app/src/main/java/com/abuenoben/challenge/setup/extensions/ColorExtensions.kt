package com.abuenoben.challenge.setup.extensions

import android.graphics.Color
import kotlin.math.roundToInt

fun Int.getOppositeColor(): Int = Color.rgb(
    255 - Color.red(this),
    255 - Color.green(this),
    255 - Color.blue(this)
)

fun Int.mixColors(color1: Int = this, color2: Int = color1.getOppositeColor(), percentFirst: Float = 0.7f): Int {

    val a1: Int = Color.alpha(color1)
    val r1: Int = Color.red(color1)
    val g1: Int = Color.green(color1)
    val b1: Int = Color.blue(color1)

    val a2: Int = Color.alpha(color2)
    val r2: Int = Color.red(color2)
    val g2: Int = Color.green(color2)
    val b2: Int = Color.blue(color2)

    val a3 = ((a1 * percentFirst) + (a2 * (1 - percentFirst))).roundToInt()
    val r3 = ((r1 * percentFirst) + (r2 * (1 - percentFirst))).roundToInt()
    val g3 = ((g1 * percentFirst) + (g2 * (1 - percentFirst))).roundToInt()
    val b3 = ((b1 * percentFirst) + (b2 * (1 - percentFirst))).roundToInt()

    return Color.argb(a3, r3, g3, b3)
}