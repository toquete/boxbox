package com.toquete.boxbox.data.driverstandings.di

import com.toquete.boxbox.data.driverstandings.source.remote.DefaultDriverStandingsRemoteDataSource
import com.toquete.boxbox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataSourceModule {

    @Binds
    fun bindsFullDriverStandingsRemoteDataSource(
        driverStandingsRemoteDataSource: DefaultDriverStandingsRemoteDataSource
    ): DriverStandingsRemoteDataSource
}
