package com.toquete.boxbox.core.common.util

import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant

fun dateAndTimeToInstant(date: String?, time: String?): Instant? {
    return when {
        date == null -> null
        time == null -> "${date}T00:00:00Z".toInstant()
        else -> "${date}T$time".toInstant()
    }
}
