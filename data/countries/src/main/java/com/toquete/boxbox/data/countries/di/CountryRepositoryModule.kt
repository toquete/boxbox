package com.toquete.boxbox.data.countries.di

import com.toquete.boxbox.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object CountryRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        @Named("internal") repository: CountryRepository
    ): CountryRepository = repository
}
