package com.toquete.boxbox.domain.usecase

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject

class GetTodayLocalDateUseCase @Inject constructor(private val clock: Clock) {

    operator fun invoke() = clock.todayIn(TimeZone.currentSystemDefault())
}
