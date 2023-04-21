package com.toquete.boxbox.data.fulldriverstandings.di

import com.toquete.boxbox.data.fulldriverstandings.source.remote.FullDriverStandingsRemoteDataSource
import com.toquete.boxbox.data.fulldriverstandings.source.remote.FullDriverStandingsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindsFullDriverStandingsRemoteDataSource(
        driverStandingsRemoteDataSource: FullDriverStandingsRemoteDataSourceImpl
    ): FullDriverStandingsRemoteDataSource
}