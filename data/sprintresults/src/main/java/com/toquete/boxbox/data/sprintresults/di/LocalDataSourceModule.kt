package com.toquete.boxbox.data.sprintresults.di

import com.toquete.boxbox.data.sprintresults.source.local.DefaultSprintResultLocalDataSource
import com.toquete.boxbox.data.sprintresults.source.local.SprintResultLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindSprintLocalDataSource(
        defaultSprintResultLocalDataSource: DefaultSprintResultLocalDataSource
    ): SprintResultLocalDataSource
}
