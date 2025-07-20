package com.toquete.boxbox.core.database.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.MIGRATION_5_6
import com.toquete.boxbox.core.database.MIGRATION_6_7
import com.toquete.boxbox.core.database.MIGRATION_8_9
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    single<BoxBoxDatabase> {
        Room.databaseBuilder(androidApplication(), BoxBoxDatabase::class.java, DATABASE)
            .setDriver(BundledSQLiteDriver())
            .addMigrations(MIGRATION_5_6, MIGRATION_6_7, MIGRATION_8_9)
            .build()
    }
}
