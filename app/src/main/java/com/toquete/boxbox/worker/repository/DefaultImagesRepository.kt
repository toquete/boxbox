package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.domain.repository.CircuitImageRepository
import com.toquete.boxbox.domain.repository.ConstructorImageRepository
import com.toquete.boxbox.domain.repository.CountryRepository
import com.toquete.boxbox.domain.repository.DriverImageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultImagesRepository @Inject constructor(
    private val countryRepository: CountryRepository,
    private val driverImageRepository: DriverImageRepository,
    private val constructorImageRepository: ConstructorImageRepository,
    private val circuitImageRepository: CircuitImageRepository
) : ImagesRepository {

    override suspend fun sync(scope: CoroutineScope) {
        scope.launch {
            awaitAll(
                async { countryRepository.sync() },
                async { driverImageRepository.sync() },
                async { constructorImageRepository.sync() },
                async { circuitImageRepository.sync() }
            )
        }
    }
}
