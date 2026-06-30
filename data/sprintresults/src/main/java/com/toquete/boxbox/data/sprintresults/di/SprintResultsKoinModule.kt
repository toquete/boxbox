package com.toquete.boxbox.data.sprintresults.di

import com.toquete.boxbox.data.sprintresults.repository.DefaultSprintResultRepository
import com.toquete.boxbox.data.sprintresults.source.remote.DefaultSprintResultRemoteDataSource
import com.toquete.boxbox.data.sprintresults.source.remote.SprintResultRemoteDataSource
import com.toquete.boxbox.domain.repository.SprintResultRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val sprintResultsModule = module {
    singleOf(::DefaultSprintResultRemoteDataSource) bind SprintResultRemoteDataSource::class
    singleOf(::DefaultSprintResultRepository) bind SprintResultRepository::class
}
