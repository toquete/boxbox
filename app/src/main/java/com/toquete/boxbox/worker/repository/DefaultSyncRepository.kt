package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.domain.repository.RaceRepository
import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DefaultSyncRepository @Inject constructor(
    private val standingsRepository: StandingsRepository,
    private val imagesRepository: ImagesRepository,
    private val raceRepository: RaceRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val clock: Clock,
    @IoDispatcher private val dispatcher: CoroutineContext
) : SyncRepository {

    override suspend fun sync() {
        withContext(dispatcher) {
            awaitAll(
                async { standingsRepository.sync() },
                async { imagesRepository.sync() },
                async { raceRepository.sync() }
            )
            userPreferencesRepository.setLastUpdatedDateInMillis(clock.now().toEpochMilliseconds())
        }
    }
}
