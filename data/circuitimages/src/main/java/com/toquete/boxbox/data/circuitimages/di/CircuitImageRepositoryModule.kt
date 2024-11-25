package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.domain.repository.CircuitImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object CircuitImageRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        @Named("internal") repository: CircuitImageRepository
    ): CircuitImageRepository = repository
}
