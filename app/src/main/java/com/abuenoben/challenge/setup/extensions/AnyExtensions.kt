package com.abuenoben.challenge.setup.extensions

import android.util.Log

val Any.LOGTAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

val safe get() = Unit

const val FILTER = "--->DevFilter"

fun Any.logWTF(text: String) {
    Log.wtf("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logWTF(text: String, exception: Exception) {
    Log.wtf("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logi(text: String) {
    Log.i("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logi(text: String, exception: Exception) {
    Log.i("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.loge(text: String) {
    Log.e("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.loge(text: String, exception: Exception) {
    Log.e("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logw(text: String) {
    Log.w("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logw(text: String, exception: Exception) {
    Log.w("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logd(text: String) {
    Log.d("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logd(text: String, exception: Exception) {
    Log.d("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logv(text: String) {
    Log.v("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logv(text: String, exception: Exception) {
    Log.v("$FILTER: ${this::class.java.simpleName}", text, exception)
}