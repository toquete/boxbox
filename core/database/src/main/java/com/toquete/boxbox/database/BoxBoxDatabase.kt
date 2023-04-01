package com.toquete.boxbox.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toquete.boxbox.database.dao.DriverStandingDao
import com.toquete.boxbox.database.model.DriverStandingEntity

@Database(entities = [DriverStandingEntity::class], version = 1)
internal abstract class BoxBoxDatabase : RoomDatabase() {

    abstract fun driverStandingDao(): DriverStandingDao
}