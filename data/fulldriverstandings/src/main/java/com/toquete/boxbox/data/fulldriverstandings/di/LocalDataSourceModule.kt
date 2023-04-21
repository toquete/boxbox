package com.toquete.boxbox.data.fulldriverstandings.di

import com.toquete.boxbox.data.fulldriverstandings.source.local.FullDriverStandingsLocalDataSource
import com.toquete.boxbox.data.fulldriverstandings.source.local.FullDriverStandingsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindFullDriverStandingLocalDataSource(
        localDataSourceImpl: FullDriverStandingsLocalDataSourceImpl
    ): FullDriverStandingsLocalDataSource
}