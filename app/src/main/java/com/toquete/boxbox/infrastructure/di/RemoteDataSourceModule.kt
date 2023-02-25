package com.toquete.boxbox.infrastructure.di

import com.toquete.boxbox.data.source.DriverStandingsDataSource
import com.toquete.boxbox.data.source.remote.DriverStandingsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindsDriverStandingsRemoteDataSource(
        driverStandingsRemoteDataSource: DriverStandingsRemoteDataSource
    ): DriverStandingsDataSource
}