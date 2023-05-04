package com.toquete.boxbox.data.fulldriverstandings.di

import com.toquete.boxbox.data.fulldriverstandings.repository.DefaultFullDriverStandingsRepository
import com.toquete.boxbox.data.fulldriverstandings.repository.FullDriverStandingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsFullDriverStandingsRepository(
        driverStandingsRepositoryImpl: DefaultFullDriverStandingsRepository
    ): FullDriverStandingsRepository
}
