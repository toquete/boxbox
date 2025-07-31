package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.domain.util.FixedClock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class GetTodayLocalDateUseCaseTest {

    private val now = LocalDateTime(
        year = 2023,
        month = 1,
        day = 1,
        hour = 0,
        minute = 0,
        second = 0,
        nanosecond = 0
    )
    private val instant = now.toInstant(TimeZone.currentSystemDefault())
    private val clock = FixedClock(instant)
    private val useCase = GetTodayLocalDateUseCase(clock)

    @Test
    fun `invoke should return today's date`() {
        val result = useCase()

        assertEquals(now.date, result)
    }
}
