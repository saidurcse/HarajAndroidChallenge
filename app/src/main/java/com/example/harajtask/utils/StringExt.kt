package com.example.harajtask.utils

import androidx.core.text.HtmlCompat
import org.joda.time.DateTime
import org.joda.time.Interval
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "yyyy/MM/dd hh:mm a"

fun String.toHtml() = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).trim()

fun Long.getInterval(): String {
    val start = DateTime(this * 1000L)
    val end = DateTime()
    val interval = Interval(start, end)
    val period = interval.toPeriod()
    return buildString {
        append("Since ")
        if (period.years > 0) {
            append(period.years)
            append(" years ")
        }
        if (period.months > 0) {
            append(period.months)
            append(" months ")
        }
        if (period.weeks > 0) {
            append(period.weeks)
            append(" weeks ")
        }
        if (period.days > 0) {
            append(period.days)
            append(" days ")
        }
        if (period.hours > 0) {
            append(period.hours)
            append(" hours ")
        }
    }
}

fun Long.getDate(): String {
    val calendar = Calendar.getInstance(Locale.ENGLISH)
    calendar.timeInMillis = this * 1000L
    val date = calendar.time
    val sdf = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
    return sdf.format(date)
}
