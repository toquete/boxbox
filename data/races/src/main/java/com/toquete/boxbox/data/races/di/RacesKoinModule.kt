package com.toquete.boxbox.data.races.di

import com.toquete.boxbox.data.races.repository.DefaultRaceRepository
import com.toquete.boxbox.data.races.source.remote.DefaultRaceRemoteDataSource
import com.toquete.boxbox.data.races.source.remote.RaceRemoteDataSource
import com.toquete.boxbox.domain.repository.RaceRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val racesModule = module {
    singleOf(::DefaultRaceRemoteDataSource) bind RaceRemoteDataSource::class
    singleOf(::DefaultRaceRepository) bind RaceRepository::class
}
