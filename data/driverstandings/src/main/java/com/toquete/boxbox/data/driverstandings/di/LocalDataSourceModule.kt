package com.toquete.boxbox.data.driverstandings.di

import com.toquete.boxbox.data.driverstandings.source.local.DefaultDriverStandingsLocalDataSource
import com.toquete.boxbox.data.driverstandings.source.local.DriverStandingsLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindDriverStandingLocalDataSource(
        localDataSourceImpl: DefaultDriverStandingsLocalDataSource
    ): DriverStandingsLocalDataSource
}
