package com.toquete.boxbox.core.preferences.di

import com.toquete.boxbox.core.preferences.repository.DefaultUserPreferencesRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
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
    fun bindUserPreferencesRepository(
        repository: DefaultUserPreferencesRepository
    ): UserPreferencesRepository
}
