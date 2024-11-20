package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource
import com.toquete.boxbox.data.circuitimages.source.remote.DefaultCircuitImageRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal fun interface RemoteDataSourceModule {

    @Binds
    fun bindCircuitImageRemoteDataSource(
        defaultCircuitImageRemoteDataSource: DefaultCircuitImageRemoteDataSource
    ): CircuitImageRemoteDataSource
}
