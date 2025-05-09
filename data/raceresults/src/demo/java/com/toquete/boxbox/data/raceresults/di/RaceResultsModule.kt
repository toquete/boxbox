package com.toquete.boxbox.data.raceresults.di

import com.toquete.boxbox.data.raceresults.repository.DefaultRaceResultRepository
import com.toquete.boxbox.data.raceresults.source.remote.DefaultRaceResultRemoteDataSource
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import com.toquete.boxbox.domain.repository.RaceResultRepository
import org.koin.dsl.module

val raceResultsDataModule = module {
    single<RaceResultRemoteDataSource> { DefaultRaceResultRemoteDataSource(json = get()) }
    single<RaceResultRepository> {
        DefaultRaceResultRepository(
            remoteDataSource = get(),
            raceResultDao = get()
        )
    }
}
