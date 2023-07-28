package com.toquete.boxbox.data.races.di

import com.toquete.boxbox.data.races.source.local.DefaultRaceLocalDataSource
import com.toquete.boxbox.data.races.source.local.RaceLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindRaceLocalDataSource(
        defaultRaceLocalDataSource: DefaultRaceLocalDataSource
    ): RaceLocalDataSource
}
