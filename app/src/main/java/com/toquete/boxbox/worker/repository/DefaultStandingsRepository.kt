package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.data.constructorcolors.repository.ConstructorColorRepository
import com.toquete.boxbox.data.constructorstandings.repository.ConstructorStandingsRepository
import com.toquete.boxbox.data.driverstandings.repository.DriverStandingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultStandingsRepository @Inject constructor(
    private val driverStandingsRepository: DriverStandingsRepository,
    private val constructorStandingsRepository: ConstructorStandingsRepository,
    private val constructorColorRepository: ConstructorColorRepository
) : StandingsRepository {

    override suspend fun sync(scope: CoroutineScope) {
        scope.launch {
            awaitAll(
                async { driverStandingsRepository.sync() },
                async { constructorStandingsRepository.sync() },
                async { constructorColorRepository.sync() }
            )
        }
    }
}
