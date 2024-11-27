package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import com.toquete.boxbox.domain.repository.RaceResultRepository
import com.toquete.boxbox.domain.repository.SprintResultRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class StandingsRepository @Inject constructor(
    private val driverStandingsRepository: DriverStandingsRepository,
    private val constructorStandingsRepository: ConstructorStandingsRepository,
    private val constructorColorRepository: ConstructorColorRepository,
    private val raceResultRepository: RaceResultRepository,
    private val sprintResultRepository: SprintResultRepository
) {

    suspend fun sync() {
        coroutineScope {
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
