package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.data.circuitimages.source.local.CircuitImageLocalDataSource
import com.toquete.boxbox.data.circuitimages.source.local.DefaultCircuitImageLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindCircuitImageLocalDataSource(
        defaultCircuitImageLocalDataSource: DefaultCircuitImageLocalDataSource
    ): CircuitImageLocalDataSource
}
