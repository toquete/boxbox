package com.toquete.boxbox.core.database.di

import com.toquete.boxbox.core.database.BoxBoxDatabase
import org.koin.dsl.module

internal const val DATABASE = "boxbox_database"

internal val commonModule = module {
    single { get<BoxBoxDatabase>().driverStandingDao() }
    single { get<BoxBoxDatabase>().driverDao() }
    single { get<BoxBoxDatabase>().constructorDao() }
    single { get<BoxBoxDatabase>().constructorStandingDao() }
    single { get<BoxBoxDatabase>().countryDao() }
    single { get<BoxBoxDatabase>().driverImageDao() }
    single { get<BoxBoxDatabase>().constructorImageDao() }
    single { get<BoxBoxDatabase>().raceDao() }
    single { get<BoxBoxDatabase>().circuitDao() }
    single { get<BoxBoxDatabase>().circuitImageDao() }
    single { get<BoxBoxDatabase>().sprintRaceResultDao() }
    single { get<BoxBoxDatabase>().constructorColorDao() }
    single { get<BoxBoxDatabase>().raceResultDao() }
    single { get<BoxBoxDatabase>().sprintRaceResultDao() }
}

val databaseModule = module {
    includes(commonModule, platformModule)
}
