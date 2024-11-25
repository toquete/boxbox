package com.toquete.boxbox.data.races.di

import com.toquete.boxbox.domain.repository.RaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object RaceRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        @Named("internal") repository: RaceRepository
    ): RaceRepository = repository
}
