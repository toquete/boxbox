package com.toquete.boxbox.core.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("DROP TABLE races")
        db.execSQL(
            "CREATE TABLE `races` (`season` TEXT NOT NULL, `round` INTEGER NOT NULL, " +
                "`raceUrl` TEXT NOT NULL, `raceName` TEXT NOT NULL, `date` TEXT NOT NULL, `time` TEXT, " +
                "`circuit_id` TEXT NOT NULL, `first_practice_date` TEXT, `first_practice_time` TEXT, " +
                "`second_practice_date` TEXT, `second_practice_time` TEXT, `third_practice_date` TEXT, " +
                "`third_practice_time` TEXT, `qualifying_date` TEXT, `qualifying_time` TEXT, `sprint_date` TEXT, " +
                "`sprint_time` TEXT, PRIMARY KEY(`season`, `round`))"
        )
    }
}
