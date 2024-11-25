package com.toquete.boxbox.data.sprintresults.di

import com.toquete.boxbox.domain.repository.SprintResultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
object SprintResultRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        @Named("internal") repository: SprintResultRepository
    ): SprintResultRepository = repository
}
