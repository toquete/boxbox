package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.domain.repository.RaceRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class DefaultSyncRepositoryTest {

    private val standingsRepository: StandingsRepository = mockk(relaxed = true)
    private val imagesRepository: ImagesRepository = mockk(relaxed = true)
    private val raceRepository: RaceRepository = mockk(relaxed = true)
    private val userPreferencesRepository: UserPreferencesRepository = mockk(relaxed = true)
    private val clock = object : Clock {
        override fun now(): Instant = Instant.fromEpochMilliseconds(epochMilliseconds = 1000)
    }

    private val repository = DefaultSyncRepository(
        standingsRepository,
        imagesRepository,
        raceRepository,
        userPreferencesRepository,
        clock
    )

    @Test
    fun `sync should call all repositories`() = runTest {
        repository.sync()

        coVerify {
            standingsRepository.sync()
            imagesRepository.sync()
            raceRepository.sync()
            userPreferencesRepository.setLastUpdatedDateInMillis(1000)
        }
    }

    @Test
    fun `sync should not save last updated date if sync fails`() = runTest {
        coEvery { raceRepository.sync() } throws Exception()

        runCatching { repository.sync() }

        coVerify(inverse = true) {
            userPreferencesRepository.setLastUpdatedDateInMillis(any())
        }
    }
}
