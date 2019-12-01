package com.abuenoben.challenge.setup.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat

fun Context.getBitmap(drawableRes: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, drawableRes)
    val canvas = Canvas()
    val bitmap =
        drawable?.intrinsicWidth?.let { Bitmap.createBitmap(it, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888) }
    canvas.setBitmap(bitmap)
    drawable?.intrinsicWidth?.let { drawable.setBounds(0, 0, it, drawable.intrinsicHeight) }
    drawable?.draw(canvas)

    return bitmap
}