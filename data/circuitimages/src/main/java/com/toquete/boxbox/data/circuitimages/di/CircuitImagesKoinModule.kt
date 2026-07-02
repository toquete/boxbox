package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.data.circuitimages.repository.DefaultCircuitImageRepository
import com.toquete.boxbox.domain.repository.CircuitImageRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val circuitImagesModule = module {
    includes(remoteDataSourceModule)
    singleOf(::DefaultCircuitImageRepository) bind CircuitImageRepository::class
}
