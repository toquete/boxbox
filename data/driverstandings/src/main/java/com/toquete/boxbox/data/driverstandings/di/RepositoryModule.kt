package com.toquete.boxbox.data.driverstandings.di

import com.toquete.boxbox.data.driverstandings.repository.DriverStandingsRepository
import com.toquete.boxbox.data.driverstandings.repository.DriverStandingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsDriverStandingsRepository(
        driverStandingsRepositoryImpl: DriverStandingsRepositoryImpl
    ): DriverStandingsRepository
}