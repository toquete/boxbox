package com.toquete.boxbox.data.drivers.di

import com.toquete.boxbox.data.drivers.source.local.DefaultDriversLocalDataSource
import com.toquete.boxbox.data.drivers.source.local.DriversLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindsDriverLocalDataSource(
        dataSourceImpl: DefaultDriversLocalDataSource
    ): DriversLocalDataSource
}
