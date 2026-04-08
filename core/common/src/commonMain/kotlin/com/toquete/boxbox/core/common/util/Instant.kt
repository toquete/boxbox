@file:OptIn(ExperimentalTime::class)

package com.toquete.boxbox.core.common.util

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

fun dateAndTimeToInstant(date: String?, time: String?): Instant? {
    return when {
        date == null -> null
        time == null -> Instant.parse("${date}T00:00:00Z")
        else -> Instant.parse("${date}T$time")
    }
}
