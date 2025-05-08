package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.domain.repository.CircuitImageRepository
import com.toquete.boxbox.domain.repository.ConstructorImageRepository
import com.toquete.boxbox.domain.repository.CountryRepository
import com.toquete.boxbox.domain.repository.DriverImageRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ImagesRepository(
    private val countryRepository: CountryRepository,
    private val driverImageRepository: DriverImageRepository,
    private val constructorImageRepository: ConstructorImageRepository,
    private val circuitImageRepository: CircuitImageRepository
) {

    suspend fun sync() {
        coroutineScope {
            awaitAll(
                async { countryRepository.sync() },
                async { driverImageRepository.sync() },
                async { constructorImageRepository.sync() },
                async { circuitImageRepository.sync() }
            )
        }
    }
}
