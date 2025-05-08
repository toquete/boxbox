package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.domain.repository.RaceRepository
import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.datetime.Clock

class DefaultSyncRepository(
    private val standingsRepository: StandingsRepository,
    private val imagesRepository: ImagesRepository,
    private val raceRepository: RaceRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val clock: Clock
) : SyncRepository {

    override suspend fun sync() = coroutineScope {
        awaitAll(
            async { standingsRepository.sync() },
            async { imagesRepository.sync() },
            async { raceRepository.sync() }
        )
        userPreferencesRepository.setLastUpdatedDateInMillis(clock.now().toEpochMilliseconds())
    }
}
