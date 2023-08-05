package com.toquete.boxbox.domain.races.usecase

import com.toquete.boxbox.core.testing.data.races
import com.toquete.boxbox.data.races.repository.RaceRepository
import com.toquete.boxbox.domain.common.usecase.GetTodayLocalDateUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Test
import kotlin.test.assertContentEquals

class GetCurrentSeasonRacesUseCaseTest {

    private val raceRepository: RaceRepository = mockk(relaxed = true)
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase = mockk(relaxed = true)
    private val useCase = GetCurrentSeasonRacesUseCase(raceRepository, getTodayLocalDateUseCase)

    @Test
    fun `invoke should return current season races`() = runTest {
        every { raceRepository.getRacesBySeason(any()) } returns flowOf(races)
        every { getTodayLocalDateUseCase() } returns LocalDate(
            year = 2023,
            monthNumber = 1,
            dayOfMonth = 1
        )

        val result = useCase().first()

        verify { raceRepository.getRacesBySeason(season = "2023") }
        assertContentEquals(races, result)
    }
}
