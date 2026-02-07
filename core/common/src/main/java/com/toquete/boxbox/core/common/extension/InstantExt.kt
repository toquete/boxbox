@file:OptIn(ExperimentalTime::class)

package com.toquete.boxbox.core.common.extension

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

private val shortMonthFormat = LocalDateTime.Format {
    monthName(MonthNames.ENGLISH_ABBREVIATED)
}

private val dayFormat = LocalDateTime.Format {
    day()
}

fun Instant?.toShortMonthString(): String {
    val date = this?.toLocalDateTime(TimeZone.currentSystemDefault()) ?: return ""
    return date.format(shortMonthFormat).uppercase()
}

fun Instant.toDayString(): String {
    val date = this.toLocalDateTime(TimeZone.currentSystemDefault())
    return date.format(dayFormat)
}
