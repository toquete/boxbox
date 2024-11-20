package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.data.constructorstandings.repository.ConstructorStandingsRepository
import com.toquete.boxbox.data.driverstandings.repository.DriverStandingsRepository
import com.toquete.boxbox.data.raceresults.repository.RaceResultRepository
import com.toquete.boxbox.data.sprintresults.repository.SprintResultRepository
import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultStandingsRepositoryTest {

    private val driverStandingsRepository: DriverStandingsRepository = mockk(relaxed = true)
    private val constructorStandingsRepository: ConstructorStandingsRepository = mockk(relaxed = true)
    private val constructorColorRepository: ConstructorColorRepository = mockk(relaxed = true)
    private val raceResultRepository: RaceResultRepository = mockk(relaxed = true)
    private val sprintRaceResultRepository: SprintResultRepository = mockk(relaxed = true)
    private val repository = DefaultStandingsRepository(
        driverStandingsRepository,
        constructorStandingsRepository,
        constructorColorRepository,
        raceResultRepository,
        sprintRaceResultRepository
    )

    @Test
    fun `sync should call all repositories`() = runTest {
        repository.sync(this)
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
