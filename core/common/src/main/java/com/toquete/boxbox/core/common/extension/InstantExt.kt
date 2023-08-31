package com.toquete.boxbox.core.common.extension

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

private const val SHORT_MONTH_PATTERN = "MMM"

fun Instant?.toShortMonthString(): String {
    val formatter = DateTimeFormatter.ofPattern(SHORT_MONTH_PATTERN)
    val date = this?.toLocalDateTime(TimeZone.currentSystemDefault())?.toJavaLocalDateTime()
    return formatter.format(date).uppercase()
}
