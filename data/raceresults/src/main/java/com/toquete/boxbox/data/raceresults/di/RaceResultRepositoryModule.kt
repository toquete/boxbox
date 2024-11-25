package com.toquete.boxbox.data.raceresults.di

import com.toquete.boxbox.domain.repository.RaceResultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object RaceResultRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        @Named("internal") repository: RaceResultRepository
    ): RaceResultRepository = repository
}
