package com.toquete.boxbox.data.driverimages.di

import com.toquete.boxbox.data.driverimages.source.local.DefaultDriverImageLocalDataSource
import com.toquete.boxbox.data.driverimages.source.local.DriverImageLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindsDriverImageLocalDataSource(
        dataSource: DefaultDriverImageLocalDataSource
    ): DriverImageLocalDataSource
}
