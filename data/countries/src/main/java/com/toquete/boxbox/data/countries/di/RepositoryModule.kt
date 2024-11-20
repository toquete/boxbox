package com.toquete.boxbox.data.countries.di

import com.toquete.boxbox.data.countries.repository.DefaultCountryRepository
import com.toquete.boxbox.domain.repository.CountryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal fun interface RepositoryModule {

    @Binds
    fun bindsCountryRepository(
        repository: DefaultCountryRepository
    ): CountryRepository
}
