package com.toquete.boxbox.data.driverstandings.di

import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object DriverStandingsRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        @Named("internal") repository: DriverStandingsRepository
    ): DriverStandingsRepository = repository
}
