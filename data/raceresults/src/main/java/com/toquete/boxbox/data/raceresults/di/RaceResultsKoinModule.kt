package com.toquete.boxbox.data.raceresults.di

import com.toquete.boxbox.data.raceresults.repository.DefaultRaceResultRepository
import com.toquete.boxbox.domain.repository.RaceResultRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val raceResultsModule = module {
    includes(remoteDataSourceModule)
    singleOf(::DefaultRaceResultRepository) bind RaceResultRepository::class
}
