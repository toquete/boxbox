package com.toquete.boxbox.data.driverimages.di

import com.toquete.boxbox.domain.repository.DriverImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object DriverImageRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        @Named("internal") repository: DriverImageRepository
    ): DriverImageRepository = repository
}
