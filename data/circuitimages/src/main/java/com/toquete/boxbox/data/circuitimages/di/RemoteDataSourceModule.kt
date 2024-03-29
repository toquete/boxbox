package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource
import com.toquete.boxbox.data.circuitimages.source.remote.DefaultCircuitImageRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindCircuitImageRemoteDataSource(
        defaultCircuitImageRemoteDataSource: DefaultCircuitImageRemoteDataSource
    ): CircuitImageRemoteDataSource
}
