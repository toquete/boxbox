package com.toquete.boxbox.preferences.di

import com.toquete.boxbox.preferences.repository.UserPreferencesRepository
import com.toquete.boxbox.preferences.repository.UserPreferencesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PreferencesRepositoryModule {

    @Binds
    abstract fun bindUserPreferencesRepository(
        repository: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository
}