package com.toquete.boxbox.data.fulldriverstandings.di

import com.toquete.boxbox.data.fulldriverstandings.repository.FullDriverStandingsRepository
import com.toquete.boxbox.data.fulldriverstandings.repository.FullDriverStandingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsFullDriverStandingsRepository(
        driverStandingsRepositoryImpl: FullDriverStandingsRepositoryImpl
    ): FullDriverStandingsRepository
}