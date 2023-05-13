package com.toquete.boxbox.core.preferences.di

import com.toquete.boxbox.core.preferences.repository.DefaultUserPreferencesRepository
import com.toquete.boxbox.core.preferences.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PreferencesRepositoryModule {

    @Binds
    abstract fun bindUserPreferencesRepository(
        repository: DefaultUserPreferencesRepository
    ): UserPreferencesRepository
}
