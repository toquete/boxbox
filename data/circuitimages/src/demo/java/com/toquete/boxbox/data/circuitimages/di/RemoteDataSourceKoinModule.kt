package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource
import com.toquete.boxbox.data.circuitimages.source.remote.DefaultCircuitImageRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val remoteDataSourceModule = module {
    singleOf(::DefaultCircuitImageRemoteDataSource) bind CircuitImageRemoteDataSource::class
}
