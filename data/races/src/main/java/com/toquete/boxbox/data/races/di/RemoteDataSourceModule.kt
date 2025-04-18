package com.toquete.boxbox.data.races.di

import com.toquete.boxbox.data.races.source.remote.DefaultRaceRemoteDataSource
import com.toquete.boxbox.data.races.source.remote.RaceRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal fun interface RemoteDataSourceModule {

    @Binds
    fun bindRaceRemoteDataSource(
        defaultRaceRemoteDataSource: DefaultRaceRemoteDataSource
    ): RaceRemoteDataSource
}
