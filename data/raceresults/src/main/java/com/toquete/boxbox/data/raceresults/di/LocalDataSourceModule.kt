package com.toquete.boxbox.data.raceresults.di

import com.toquete.boxbox.data.raceresults.source.local.DefaultRaceResultLocalDataSource
import com.toquete.boxbox.data.raceresults.source.local.RaceResultLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindRaceLocalDataSource(
        defaultRaceResultLocalDataSource: DefaultRaceResultLocalDataSource
    ): RaceResultLocalDataSource
}
