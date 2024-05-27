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

val MIGRATION_6_7 = object : Migration(6, 7) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("DROP TABLE drivers")
        db.execSQL(
            "CREATE TABLE `drivers` (`id` TEXT NOT NULL, `number` TEXT NOT NULL, `code` TEXT, " +
                "`url` TEXT NOT NULL, `first_name` TEXT NOT NULL, `last_name` TEXT NOT NULL, " +
                "`date_of_birth` TEXT NOT NULL, `nationality` TEXT NOT NULL, PRIMARY KEY(`id`))"
        )
    }
}

val MIGRATION_8_9 = object : Migration(8, 9) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("DROP TABLE race_results")
        db.execSQL(
            "CREATE TABLE `race_results` (`season` TEXT NOT NULL, `round` INTEGER NOT NULL, " +
                "`position` INTEGER NOT NULL, `points` INTEGER NOT NULL, `driver_id` TEXT NOT NULL, " +
                "`constructor_id` TEXT NOT NULL, `grid_position` INTEGER NOT NULL, `laps` TEXT NOT NULL, " +
                "`status` TEXT NOT NULL, `time` TEXT, PRIMARY KEY(`season`, `round`, `position`))"
        )
    }
}
