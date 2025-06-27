@file:OptIn(ExperimentalTime::class)

package com.toquete.boxbox.core.common.extension

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.dateWithTimeIntervalSince1970
import platform.Foundation.localTimeZone
import platform.Foundation.localeWithLocaleIdentifier
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

private const val SHORT_MONTH_PATTERN = "LLL" // Example: Jan, Feb, etc.
private const val DAY_PATTERN = "dd" // Example: 01, 23, etc.

actual fun Instant?.toShortMonthString(): String {
    if (this == null) return ""

    val date = this.toNSDate()
    val formatter = NSDateFormatter().apply {
        dateFormat = SHORT_MONTH_PATTERN
        locale = NSLocale.localeWithLocaleIdentifier("en_US")
        timeZone = NSTimeZone.localTimeZone
    }
    return formatter.stringFromDate(date).uppercase()
}

actual fun Instant.toDayString(): String {
    val date = this.toNSDate()
    val formatter = NSDateFormatter().apply {
        dateFormat = DAY_PATTERN
        locale = NSLocale.localeWithLocaleIdentifier("en_US")
        timeZone = NSTimeZone.localTimeZone
    }
    return formatter.stringFromDate(date)
}

// Helper to convert kotlinx.datetime.Instant to platform.Foundation.NSDate
private fun Instant.toNSDate(): NSDate {
    return NSDate.dateWithTimeIntervalSince1970(this.epochSeconds.toDouble())
}
