package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.core.testing.data.raceResults
import com.toquete.boxbox.domain.repository.RaceResultRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Test
import kotlin.test.assertContentEquals

class GetCurrentSeasonRaceResultsUseCaseTest {

    private val raceResultRepository: RaceResultRepository = mockk(relaxed = true)
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase = mockk(relaxed = true)
    private val useCase = GetCurrentSeasonRaceResultsUseCase(
        raceResultRepository = raceResultRepository,
        getTodayLocalDateUseCase = getTodayLocalDateUseCase
    )

    @Test
    fun `invoke should return current season race results`() = runTest {
        every { raceResultRepository.getRaceResultsBySeasonAndRound(any(), any()) } returns flowOf(raceResults)
        every { getTodayLocalDateUseCase() } returns LocalDate(year = 2023, month = 1, day = 1)

        val result = useCase(round = 1).first()

        verify { raceResultRepository.getRaceResultsBySeasonAndRound(season = "2023", round = 1) }
        assertContentEquals(raceResults, result)
    }
}
