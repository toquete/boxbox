package com.toquete.boxbox.data.driverstandings.di

import com.toquete.boxbox.data.driverstandings.repository.DefaultDriverStandingsRepository
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal fun interface RepositoryModule {

    @Binds
    @Named("internal")
    fun bindsFullDriverStandingsRepository(
        driverStandingsRepositoryImpl: DefaultDriverStandingsRepository
    ): DriverStandingsRepository
}
