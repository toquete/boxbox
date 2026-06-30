package com.toquete.boxbox.core.database.di

import androidx.room.Room
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.MIGRATION_11_12
import com.toquete.boxbox.core.database.MIGRATION_12_13
import com.toquete.boxbox.core.database.MIGRATION_5_6
import com.toquete.boxbox.core.database.MIGRATION_6_7
import com.toquete.boxbox.core.database.MIGRATION_8_9
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "boxbox_database"

val databaseModule = module {
    single<BoxBoxDatabase> {
        Room.databaseBuilder(
            androidContext(),
            BoxBoxDatabase::class.java,
            DATABASE_NAME
        )
            .addMigrations(
                MIGRATION_5_6,
                MIGRATION_6_7,
                MIGRATION_8_9,
                MIGRATION_11_12,
                MIGRATION_12_13
            )
            .build()
    }

    single { get<BoxBoxDatabase>().circuitDao() }
    single { get<BoxBoxDatabase>().circuitImageDao() }
    single { get<BoxBoxDatabase>().constructorColorDao() }
    single { get<BoxBoxDatabase>().constructorDao() }
    single { get<BoxBoxDatabase>().constructorImageDao() }
    single { get<BoxBoxDatabase>().constructorStandingDao() }
    single { get<BoxBoxDatabase>().countryDao() }
    single { get<BoxBoxDatabase>().driverDao() }
    single { get<BoxBoxDatabase>().driverImageDao() }
    single { get<BoxBoxDatabase>().driverStandingDao() }
    single { get<BoxBoxDatabase>().raceDao() }
    single { get<BoxBoxDatabase>().raceResultDao() }
    single { get<BoxBoxDatabase>().sprintRaceResultDao() }
}
