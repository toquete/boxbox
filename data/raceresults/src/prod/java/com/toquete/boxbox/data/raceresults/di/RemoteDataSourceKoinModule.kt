package com.toquete.boxbox.data.raceresults.di

import com.toquete.boxbox.data.raceresults.source.remote.DefaultRaceResultRemoteDataSource
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val remoteDataSourceModule = module {
    singleOf(::DefaultRaceResultRemoteDataSource) bind RaceResultRemoteDataSource::class
}
