package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.data.circuitimages.repository.DefaultCircuitImageRepository
import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource
import com.toquete.boxbox.data.circuitimages.source.remote.DefaultCircuitImageRemoteDataSource
import com.toquete.boxbox.domain.repository.CircuitImageRepository
import org.koin.dsl.module

val circuitImageModule = module {
    single<CircuitImageRemoteDataSource> { DefaultCircuitImageRemoteDataSource(remoteDatabase = get()) }
    single<CircuitImageRepository> {
        DefaultCircuitImageRepository(
            remoteDataSource = get(),
            circuitImageDao = get()
        )
    }
}
