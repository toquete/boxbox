package com.toquete.boxbox.core.database.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.MIGRATION_5_6
import com.toquete.boxbox.core.database.MIGRATION_6_7
import com.toquete.boxbox.core.database.MIGRATION_8_9
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DATABASE = "boxbox_database"

val databaseModule = module {
    single<BoxBoxDatabase> {
        Room.databaseBuilder(androidApplication(), BoxBoxDatabase::class.java, DATABASE)
            .setDriver(BundledSQLiteDriver())
            .addMigrations(MIGRATION_5_6, MIGRATION_6_7, MIGRATION_8_9)
            .build()
    }
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
