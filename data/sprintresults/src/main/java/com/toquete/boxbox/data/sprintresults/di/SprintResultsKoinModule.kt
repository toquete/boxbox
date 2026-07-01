package com.toquete.boxbox.data.sprintresults.di

import com.toquete.boxbox.data.sprintresults.repository.DefaultSprintResultRepository
import com.toquete.boxbox.domain.repository.SprintResultRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sprintResultsModule = module {
    includes(remoteDataSourceModule)
    singleOf(::DefaultSprintResultRepository) bind SprintResultRepository::class
}
