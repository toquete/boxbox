package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.data.sprintresults.repository.SprintResultRepository
import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import com.toquete.boxbox.domain.repository.RaceResultRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultStandingsRepository @Inject constructor(
    private val driverStandingsRepository: DriverStandingsRepository,
    private val constructorStandingsRepository: ConstructorStandingsRepository,
    private val constructorColorRepository: ConstructorColorRepository,
    private val raceResultRepository: RaceResultRepository,
    private val sprintResultRepository: SprintResultRepository
) : StandingsRepository {

    override suspend fun sync(scope: CoroutineScope) {
        scope.launch {
            awaitAll(
                async { driverStandingsRepository.sync() },
                async { constructorStandingsRepository.sync() },
                async { constructorColorRepository.sync() },
                async { raceResultRepository.sync() },
                async { sprintResultRepository.sync() }
            )
        }
    }
}
