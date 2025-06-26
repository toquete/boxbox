package com.toquete.boxbox.domain.usecase

import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class GetTodayLocalDateUseCase @Inject constructor(private val clock: Clock) {

    operator fun invoke() = clock.todayIn(TimeZone.currentSystemDefault())
}
