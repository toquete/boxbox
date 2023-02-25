package com.toquete.boxbox.infrastructure.di

import com.toquete.boxbox.data.repository.DriverStandingsRepositoryImpl
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsDriverStandingsRepository(
        driverStandingsRepositoryImpl: DriverStandingsRepositoryImpl
    ): DriverStandingsRepository
}