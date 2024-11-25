package com.toquete.boxbox.data.constructorimages.di

import com.toquete.boxbox.domain.repository.ConstructorImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object ConstructorImageRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        @Named("internal") repository: ConstructorImageRepository
    ): ConstructorImageRepository = repository
}
