package com.toquete.boxox.data.driverstandings.di

import com.toquete.boxox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import com.toquete.boxox.data.driverstandings.source.remote.DriverStandingsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindsDriverStandingsRemoteDataSource(
        driverStandingsRemoteDataSource: DriverStandingsRemoteDataSourceImpl
    ): DriverStandingsRemoteDataSource
}