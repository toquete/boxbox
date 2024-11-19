package com.toquete.boxbox.domain.sprintresults.usecase

import com.toquete.boxbox.core.testing.data.sprintRaceResults
import com.toquete.boxbox.data.sprintresults.repository.SprintResultRepository
import com.toquete.boxbox.domain.usecase.GetTodayLocalDateUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Test
import kotlin.test.assertContentEquals

class GetCurrentSeasonSprintResultsUseCaseTest {

    private val sprintResultRepository: SprintResultRepository = mockk(relaxed = true)
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase = mockk(relaxed = true)
    private val useCase = GetCurrentSeasonSprintResultsUseCase(
        sprintResultRepository = sprintResultRepository,
        getTodayLocalDateUseCase = getTodayLocalDateUseCase
    )

    @Test
    fun `invoke should return current season race results`() = runTest {
        every {
            sprintResultRepository.getSprintResultsBySeasonAndRound(any(), any())
        } returns flowOf(sprintRaceResults)
        every { getTodayLocalDateUseCase() } returns LocalDate(year = 2023, monthNumber = 1, dayOfMonth = 1)

        val result = useCase(round = 1).first()

        verify { sprintResultRepository.getSprintResultsBySeasonAndRound(season = "2023", round = 1) }
        assertContentEquals(sprintRaceResults, result)
    }
}
