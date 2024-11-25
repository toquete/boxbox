package com.toquete.boxbox.core.preferences.di

import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object UserPreferencesRepositoryModule {

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        @Named("internal") repository: UserPreferencesRepository
    ): UserPreferencesRepository = repository
}
