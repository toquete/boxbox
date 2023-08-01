package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.common.util.Syncable
import com.toquete.boxbox.data.races.repository.RaceRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DefaultSyncRepository @Inject constructor(
    private val standingsRepository: StandingsRepository,
    private val imagesRepository: ImagesRepository,
    private val raceRepository: RaceRepository,
    @IoDispatcher private val dispatcher: CoroutineContext
) : Syncable {

    override suspend fun sync() {
        withContext(dispatcher) {
            awaitAll(
                async { standingsRepository.sync(this) },
                async { imagesRepository.sync(this) },
                async { raceRepository.sync() }
            )
        }
    }
}
