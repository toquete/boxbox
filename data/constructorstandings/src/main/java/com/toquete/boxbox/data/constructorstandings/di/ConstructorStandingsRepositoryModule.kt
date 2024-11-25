package com.toquete.boxbox.data.constructorstandings.di

import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object ConstructorStandingsRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        @Named("internal") repository: ConstructorStandingsRepository
    ): ConstructorStandingsRepository = repository
}
