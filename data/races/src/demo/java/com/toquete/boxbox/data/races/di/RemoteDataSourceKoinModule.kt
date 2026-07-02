package com.toquete.boxbox.data.races.di

import com.toquete.boxbox.data.races.source.remote.DefaultRaceRemoteDataSource
import com.toquete.boxbox.data.races.source.remote.RaceRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val remoteDataSourceModule = module {
    singleOf(::DefaultRaceRemoteDataSource) bind RaceRemoteDataSource::class
}
