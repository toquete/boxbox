package com.toquete.boxbox.data.raceresults.di

import com.toquete.boxbox.data.raceresults.repository.DefaultRaceResultRepository
import com.toquete.boxbox.data.raceresults.source.remote.DefaultRaceResultRemoteDataSource
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import com.toquete.boxbox.domain.repository.RaceResultRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val raceResultsModule = module {
    singleOf(::DefaultRaceResultRemoteDataSource) bind RaceResultRemoteDataSource::class
    singleOf(::DefaultRaceResultRepository) bind RaceResultRepository::class
}
