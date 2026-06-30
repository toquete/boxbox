package com.toquete.boxbox.data.driverstandings.di

import com.toquete.boxbox.data.driverstandings.repository.DefaultDriverStandingsRepository
import com.toquete.boxbox.data.driverstandings.source.remote.DefaultDriverStandingsRemoteDataSource
import com.toquete.boxbox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val driverStandingsModule = module {
    singleOf(::DefaultDriverStandingsRemoteDataSource) bind DriverStandingsRemoteDataSource::class
    singleOf(::DefaultDriverStandingsRepository) bind DriverStandingsRepository::class
}
