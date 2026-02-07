package com.toquete.boxbox.sync.repository

import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import com.toquete.boxbox.domain.repository.RaceResultRepository
import com.toquete.boxbox.domain.repository.SprintResultRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class StandingsRepositoryTest {

    private val driverStandingsRepository: DriverStandingsRepository = mockk(relaxed = true)
    private val constructorStandingsRepository: ConstructorStandingsRepository = mockk(relaxed = true)
    private val constructorColorRepository: ConstructorColorRepository = mockk(relaxed = true)
    private val raceResultRepository: RaceResultRepository = mockk(relaxed = true)
    private val sprintRaceResultRepository: SprintResultRepository = mockk(relaxed = true)
    private val repository = StandingsRepository(
        driverStandingsRepository,
        constructorStandingsRepository,
        constructorColorRepository,
        raceResultRepository,
        sprintRaceResultRepository
    )

    @Test
    fun `sync should call all repositories`() = runTest {
        repository.sync()
        advanceUntilIdle()

        coVerify {
            driverStandingsRepository.sync()
            constructorStandingsRepository.sync()
            constructorColorRepository.sync()
            raceResultRepository.sync()
            sprintRaceResultRepository.sync()
        }
    }
}
