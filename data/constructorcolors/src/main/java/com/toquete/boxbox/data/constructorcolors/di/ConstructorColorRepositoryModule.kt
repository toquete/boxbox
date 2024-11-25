package com.toquete.boxbox.data.constructorcolors.di

import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object ConstructorColorRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        @Named("internal") repository: ConstructorColorRepository
    ): ConstructorColorRepository = repository
}
