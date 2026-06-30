package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.data.circuitimages.repository.DefaultCircuitImageRepository
import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource
import com.toquete.boxbox.data.circuitimages.source.remote.DefaultCircuitImageRemoteDataSource
import com.toquete.boxbox.domain.repository.CircuitImageRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val circuitImagesModule = module {
    singleOf(::DefaultCircuitImageRemoteDataSource) bind CircuitImageRemoteDataSource::class
    singleOf(::DefaultCircuitImageRepository) bind CircuitImageRepository::class
}
