@file:OptIn(ExperimentalTime::class)

package com.toquete.boxbox.core.common.extension

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

expect fun Instant?.toShortMonthString(): String
expect fun Instant.toDayString(): String
