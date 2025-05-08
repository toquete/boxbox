package com.toquete.boxbox.data.raceresults.di

import com.toquete.boxbox.data.raceresults.repository.DefaultRaceResultRepository
import com.toquete.boxbox.data.raceresults.source.remote.DefaultRaceResultRemoteDataSource
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import com.toquete.boxbox.domain.repository.RaceResultRepository
import org.koin.dsl.module

val raceResultsModule = module {
    single<RaceResultRemoteDataSource> { DefaultRaceResultRemoteDataSource(service = get()) }
    single<RaceResultRepository> {
        DefaultRaceResultRepository(
            remoteDataSource = get(),
            raceResultDao = get()
        )
    }
}
