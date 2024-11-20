package com.toquete.boxbox.data.raceresults.di

import com.toquete.boxbox.data.raceresults.source.remote.DefaultRaceResultRemoteDataSource
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataSourceModule {

    @Binds
    fun bindRaceRemoteDataSource(
        defaultRaceResultRemoteDataSource: DefaultRaceResultRemoteDataSource
    ): RaceResultRemoteDataSource
}
