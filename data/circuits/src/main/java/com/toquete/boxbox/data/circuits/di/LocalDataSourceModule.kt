package com.toquete.boxbox.data.circuits.di

import com.toquete.boxbox.data.circuits.source.local.CircuitLocalDataSource
import com.toquete.boxbox.data.circuits.source.local.DefaultCircuitLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindCircuitLocalDataSource(
        defaultCircuitLocalDataSource: DefaultCircuitLocalDataSource
    ): CircuitLocalDataSource
}
