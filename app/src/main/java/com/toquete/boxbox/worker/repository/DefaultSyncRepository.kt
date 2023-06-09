package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.data.constructorimages.repository.ConstructorImageRepository
import com.toquete.boxbox.data.constructorstandings.repository.ConstructorStandingsRepository
import com.toquete.boxbox.data.countries.repository.CountryRepository
import com.toquete.boxbox.data.driverimages.repository.DriverImageRepository
import com.toquete.boxbox.data.driverstandings.repository.DriverStandingsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DefaultSyncRepository @Inject constructor(
    private val driverStandingsRepository: DriverStandingsRepository,
    private val constructorStandingsRepository: ConstructorStandingsRepository,
    private val countryRepository: CountryRepository,
    private val driverImageRepository: DriverImageRepository,
    private val constructorImageRepository: ConstructorImageRepository,
    @IoDispatcher private val dispatcher: CoroutineContext
) : SyncRepository {

    override suspend fun sync(): Boolean {
        return withContext(dispatcher) {
            awaitAll(
                async { driverImageRepository.sync() },
                async { constructorImageRepository.sync() },
                async { countryRepository.sync() },
                async { driverStandingsRepository.sync() },
                async { constructorStandingsRepository.sync() }
            ).all { it }
        }
    }
}
