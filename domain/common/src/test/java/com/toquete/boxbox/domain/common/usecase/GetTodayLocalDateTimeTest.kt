package com.toquete.boxbox.domain.common.usecase

import com.toquete.boxbox.domain.common.util.FixedClock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.junit.Test
import kotlin.test.assertEquals

class GetTodayLocalDateTimeTest {

    private val now = LocalDateTime(
        year = 2023,
        monthNumber = 1,
        dayOfMonth = 1,
        hour = 0,
        minute = 0,
        second = 0
    )
    private val instant = now.toInstant(TimeZone.currentSystemDefault())
    private val clock = FixedClock(instant)
    private val useCase = GetTodayLocalDate(clock)

    @Test
    fun `invoke should return today's date`() {
        val result = useCase()

        assertEquals(now.date, result)
    }
}
