package com.toquete.boxbox.data.fulldriverstandings.di

import com.toquete.boxbox.data.fulldriverstandings.source.local.DefaultFullDriverStandingsLocalDataSource
import com.toquete.boxbox.data.fulldriverstandings.source.local.FullDriverStandingsLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindFullDriverStandingLocalDataSource(
        localDataSourceImpl: DefaultFullDriverStandingsLocalDataSource
    ): FullDriverStandingsLocalDataSource
}