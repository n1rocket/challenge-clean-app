package com.abuenoben.challenge.setup.extensions

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String?.getDateFormatted(): CharSequence? {
    val date = this?.getDateWithServerTimeStamp()
    val dateFormat = SimpleDateFormat("d MMMM yyyy '|' HH:mm",
        Locale("es"))
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    return dateFormat.format(date)
}

fun String.getDateWithServerTimeStamp(): Date? {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        Locale.getDefault()
    )
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

@SuppressLint("SimpleDateFormat")
fun Calendar.getTimestamp(): String {
    val df = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS")
    val date = this.time
    return df.format(date)
}

fun getActualCalendar(): Calendar {
    return Calendar.getInstance()
}

@SuppressLint("SimpleDateFormat")
fun Int.getDateTimeFromEpoch(pattern: String = "dd/MM/yyyy"): String? {
    return try {
        val sdf = SimpleDateFormat(pattern)

        logd("------> DATE: ${this.toLong()}")

        val netDate = Date(this.toLong() * 1000)
        sdf.format(netDate)
    } catch (e: Exception) {
        logd(e.toString())
        ""
    }
}