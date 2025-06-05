package com.toquete.boxbox.core.common.util

import kotlinx.datetime.Instant

fun dateAndTimeToInstant(date: String?, time: String?): Instant? {
    return when {
        date == null -> null
        time == null -> Instant.parse("${date}T00:00:00Z")
        else -> Instant.parse("${date}T$time")
    }
}
