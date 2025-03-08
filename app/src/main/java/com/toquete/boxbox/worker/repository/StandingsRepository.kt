package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import com.toquete.boxbox.domain.repository.RaceResultRepository
import com.toquete.boxbox.domain.repository.SprintResultRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

private const val DELAY = 1

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
                async {
                    driverStandingsRepository.sync()
                    delay(DELAY.seconds)
                    constructorStandingsRepository.sync()
                    delay(DELAY.seconds)
                    raceResultRepository.sync()
                    delay(DELAY.seconds)
                    sprintResultRepository.sync()
                },
                async { constructorColorRepository.sync() },
            )
        }
    }
}
