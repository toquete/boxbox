package com.toquete.boxbox.data.races.di

import com.toquete.boxbox.data.races.repository.DefaultRaceRepository
import com.toquete.boxbox.data.races.source.remote.DefaultRaceRemoteDataSource
import com.toquete.boxbox.data.races.source.remote.RaceRemoteDataSource
import com.toquete.boxbox.domain.repository.RaceRepository
import org.koin.dsl.module

val racesModule = module {
    single<RaceRemoteDataSource> { DefaultRaceRemoteDataSource(service = get()) }
    single<RaceRepository> {
        DefaultRaceRepository(
            remoteDataSource = get(),
            raceDao = get(),
            circuitDao = get()
        )
    }
}
