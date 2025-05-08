package com.toquete.boxbox.data.sprintresults.di

import com.toquete.boxbox.data.sprintresults.repository.DefaultSprintResultRepository
import com.toquete.boxbox.data.sprintresults.source.remote.DefaultSprintResultRemoteDataSource
import com.toquete.boxbox.data.sprintresults.source.remote.SprintResultRemoteDataSource
import com.toquete.boxbox.domain.repository.SprintResultRepository
import org.koin.dsl.module

val sprintResultsModule = module {
    single<SprintResultRemoteDataSource> { DefaultSprintResultRemoteDataSource(service = get()) }
    single<SprintResultRepository> {
        DefaultSprintResultRepository(
            remoteDataSource = get(),
            sprintRaceResultDao = get()
        )
    }
}
