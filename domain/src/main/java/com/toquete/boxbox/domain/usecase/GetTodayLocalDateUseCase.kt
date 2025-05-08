package com.toquete.boxbox.domain.usecase

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

class GetTodayLocalDateUseCase(private val clock: Clock) {

    operator fun invoke() = clock.todayIn(TimeZone.currentSystemDefault())
}
