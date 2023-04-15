package com.toquete.boxox.data.driverstandings.di

import com.toquete.boxox.data.driverstandings.source.local.DriverStandingsLocalDataSource
import com.toquete.boxox.data.driverstandings.source.local.DriverStandingsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindDriverStandingLocalDataSource(
        localDataSourceImpl: DriverStandingsLocalDataSourceImpl
    ): DriverStandingsLocalDataSource
}