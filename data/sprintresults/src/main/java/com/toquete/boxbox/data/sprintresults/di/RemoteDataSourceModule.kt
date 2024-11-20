package com.toquete.boxbox.data.sprintresults.di

import com.toquete.boxbox.data.sprintresults.source.remote.DefaultSprintResultRemoteDataSource
import com.toquete.boxbox.data.sprintresults.source.remote.SprintResultRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataSourceModule {

    @Binds
    fun bindSprintRemoteDataSource(
        defaultSprintResultRemoteDataSource: DefaultSprintResultRemoteDataSource
    ): SprintResultRemoteDataSource
}
