package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.domain.repository.RaceRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultSyncRepositoryTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val standingsRepository: StandingsRepository = mockk(relaxed = true)
    private val imagesRepository: ImagesRepository = mockk(relaxed = true)
    private val raceRepository: RaceRepository = mockk(relaxed = true)
    private val repository = DefaultSyncRepository(
        standingsRepository,
        imagesRepository,
        raceRepository,
        dispatcher
    )

    @Test
    fun `sync should call all repositories`() = runTest(dispatcher) {
        repository.sync()

        coVerify {
            standingsRepository.sync()
            imagesRepository.sync()
            raceRepository.sync()
        }
    }
}
