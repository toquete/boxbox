package com.toquete.boxbox.core.common.util

import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant

fun dateAndTimeToInstant(date: String?, time: String?): Instant? {
    if (date == null || time == null) {
        return null
    }

    return "${date}T$time".toInstant()
}
