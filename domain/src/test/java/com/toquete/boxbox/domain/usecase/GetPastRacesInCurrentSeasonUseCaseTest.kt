package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.core.testing.data.races
import com.toquete.boxbox.domain.repository.RaceRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Test
import kotlin.test.assertContentEquals

class GetPastRacesInCurrentSeasonUseCaseTest {

    private val raceRepository: RaceRepository = mockk(relaxed = true)
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase = mockk(relaxed = true)
    private val useCase = GetPastRacesInCurrentSeasonUseCase(raceRepository, getTodayLocalDateUseCase)

    @Test
    fun `invoke should return upcoming races in current season`() = runTest {
        every { raceRepository.getPastRacesBySeason(any(), any()) } returns flowOf(races)
        every { getTodayLocalDateUseCase() } returns LocalDate(
            year = 2023,
            monthNumber = 1,
            dayOfMonth = 1
        )

        val result = useCase().first()

        verify { raceRepository.getPastRacesBySeason(season = "2023", today = "2023-01-01") }
        assertContentEquals(races, result)
    }
}
