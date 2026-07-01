package com.toquete.boxbox.domain.usecase

import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class GetTodayLocalDateUseCase constructor(private val clock: Clock) {

    operator fun invoke() = clock.todayIn(TimeZone.currentSystemDefault())
}
