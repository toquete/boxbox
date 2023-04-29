package com.toquete.boxbox.data.fulldriverstandings.di

import com.toquete.boxbox.data.fulldriverstandings.source.remote.DefaultFullDriverStandingsRemoteDataSource
import com.toquete.boxbox.data.fulldriverstandings.source.remote.FullDriverStandingsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindsFullDriverStandingsRemoteDataSource(
        driverStandingsRemoteDataSource: DefaultFullDriverStandingsRemoteDataSource
    ): FullDriverStandingsRemoteDataSource
}