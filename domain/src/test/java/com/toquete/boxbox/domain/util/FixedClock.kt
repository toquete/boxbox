package com.toquete.boxbox.domain.util

import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class FixedClock(private val fixedTime: Instant) : Clock {

    override fun now(): Instant = fixedTime
}
