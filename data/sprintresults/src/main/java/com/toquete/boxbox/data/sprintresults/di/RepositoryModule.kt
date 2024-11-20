package com.toquete.boxbox.data.sprintresults.di

import com.toquete.boxbox.data.sprintresults.repository.DefaultSprintResultRepository
import com.toquete.boxbox.domain.repository.SprintResultRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal fun interface RepositoryModule {

    @Binds
    fun bindsSprintResultRepository(
        defaultSprintResultRepository: DefaultSprintResultRepository
    ): SprintResultRepository
}
