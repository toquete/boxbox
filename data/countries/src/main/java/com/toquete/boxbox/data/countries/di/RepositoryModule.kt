package com.toquete.boxbox.data.countries.di

import com.toquete.boxbox.data.countries.repository.CountryRepository
import com.toquete.boxbox.data.countries.repository.DefaultCountryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsCountryRepository(
        repository: DefaultCountryRepository
    ): CountryRepository
}
