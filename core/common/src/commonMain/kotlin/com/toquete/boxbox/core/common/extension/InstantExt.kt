package com.toquete.boxbox.core.common.extension

import kotlinx.datetime.Instant

expect fun Instant?.toShortMonthString(): String
expect fun Instant.toDayString(): String
