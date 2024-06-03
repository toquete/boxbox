package com.toquete.boxbox.data.sprintresults.di

import com.toquete.boxbox.data.sprintresults.repository.DefaultSprintResultRepository
import com.toquete.boxbox.data.sprintresults.repository.SprintResultRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsSprintResultRepository(
        defaultSprintResultRepository: DefaultSprintResultRepository
    ): SprintResultRepository
}
