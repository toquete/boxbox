package com.toquete.boxbox.data.driverstandings.di

import com.toquete.boxbox.data.driverstandings.repository.DefaultDriverStandingsRepository
import com.toquete.boxbox.data.driverstandings.source.remote.DefaultDriverStandingsRemoteDataSource
import com.toquete.boxbox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import org.koin.dsl.module

val driverStandingsDataModule = module {
    single<DriverStandingsRemoteDataSource> { DefaultDriverStandingsRemoteDataSource(json = get()) }
    single<DriverStandingsRepository> {
        DefaultDriverStandingsRepository(
            remoteDataSource = get(),
            driverStandingDao = get(),
            driverDao = get(),
            constructorDao = get()
        )
    }
}
