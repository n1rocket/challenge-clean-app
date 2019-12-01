package com.abuenoben.challenge.setup.extensions

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.squareup.picasso.Picasso

fun ImageView.picasso(url: String?, placeholder: Int, error: Int) = url?.let {
    if (url != "") Picasso.get().load(url).placeholder(placeholder).error(error).into(this)
    else this.setImageDrawable(context.getDrawable(error))
}

private fun ImageView.roundDrawable(drawableId: Int): RoundedBitmapDrawable {
    val placeholder = BitmapFactory.decodeResource(this.resources, drawableId)
    val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(this.resources, placeholder)
    circularBitmapDrawable.isCircular = true
    return circularBitmapDrawable
}
