package com.windnews.data.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

fun String.toDateTime(): Date? {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val parsedDate: Date = inputFormat.parse(this)
        Log.e("DATE", "Test date format $parsedDate")
        parsedDate
    }catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun String.getFormattedDateTime(pattern: String = "dd MMMM yyyy"): String? {
    return try {
        val outputFormat = SimpleDateFormat(pattern)
        val parsedDate: Date? = this.toDateTime()
        val formatted = parsedDate?.let {
            outputFormat.format(it)
        }
        Log.e("DATE", "Test formatted $formatted")
        formatted
    }catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
