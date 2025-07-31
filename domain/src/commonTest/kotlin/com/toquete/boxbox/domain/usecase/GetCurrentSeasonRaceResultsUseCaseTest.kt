package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.domain.fake.FakeRaceResultRepository
import com.toquete.boxbox.domain.mock.raceResults
import com.toquete.boxbox.domain.repository.RaceResultRepository
import com.toquete.boxbox.domain.util.FixedClock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class GetCurrentSeasonRaceResultsUseCaseTest {

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

    private val raceResultRepository: RaceResultRepository = FakeRaceResultRepository()
    private val getTodayLocalDateUseCase = GetTodayLocalDateUseCase(clock)
    private val useCase = GetCurrentSeasonRaceResultsUseCase(
        raceResultRepository = raceResultRepository,
        getTodayLocalDateUseCase = getTodayLocalDateUseCase
    )

    @Test
    fun `invoke should return current season race results`() = runTest {
        val result = useCase(round = 1).first()
        assertContentEquals(raceResults, result)
    }
}
