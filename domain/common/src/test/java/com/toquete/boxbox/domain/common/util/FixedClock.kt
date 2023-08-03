package com.toquete.boxbox.domain.common.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class FixedClock(private val fixedTime: Instant) : Clock {

    override fun now(): Instant = fixedTime
}
