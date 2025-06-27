@file:OptIn(ExperimentalTime::class)

package com.toquete.boxbox.core.common.extension

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

private const val SHORT_MONTH_PATTERN = "MMM"
private const val DAY_PATTERN = "dd"

actual fun Instant?.toShortMonthString(): String {
    val formatter = DateTimeFormatter.ofPattern(SHORT_MONTH_PATTERN, Locale.US)
    val date = this?.toLocalDateTime(TimeZone.currentSystemDefault())?.toJavaLocalDateTime()
    return formatter.format(date).uppercase()
}

actual fun Instant.toDayString(): String {
    val formatter = DateTimeFormatter.ofPattern(DAY_PATTERN, Locale.US)
    val date = this.toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime()
    return formatter.format(date)
}
