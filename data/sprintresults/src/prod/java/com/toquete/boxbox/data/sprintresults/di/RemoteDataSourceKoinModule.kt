package com.toquete.boxbox.data.sprintresults.di

import com.toquete.boxbox.data.sprintresults.source.remote.DefaultSprintResultRemoteDataSource
import com.toquete.boxbox.data.sprintresults.source.remote.SprintResultRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val remoteDataSourceModule = module {
    singleOf(::DefaultSprintResultRemoteDataSource) bind SprintResultRemoteDataSource::class
}
