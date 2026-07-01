package com.toquete.boxbox.data.races.di

import com.toquete.boxbox.data.races.repository.DefaultRaceRepository
import com.toquete.boxbox.domain.repository.RaceRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val racesModule = module {
    includes(remoteDataSourceModule)
    singleOf(::DefaultRaceRepository) bind RaceRepository::class
}
