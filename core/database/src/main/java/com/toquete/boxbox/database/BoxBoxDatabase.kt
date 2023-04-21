package com.toquete.boxbox.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toquete.boxbox.database.dao.ConstructorDao
import com.toquete.boxbox.database.dao.DriverDao
import com.toquete.boxbox.database.dao.DriverStandingDao
import com.toquete.boxbox.database.dao.FullDriverStandingDao
import com.toquete.boxbox.database.model.ConstructorEntity
import com.toquete.boxbox.database.model.ConstructorImage
import com.toquete.boxbox.database.model.CountryEntity
import com.toquete.boxbox.database.model.DriverEntity
import com.toquete.boxbox.database.model.DriverImage
import com.toquete.boxbox.database.model.DriverStandingEntity

@Database(
    entities = [
        DriverStandingEntity::class,
        DriverEntity::class,
        ConstructorEntity::class,
        CountryEntity::class,
        DriverImage::class,
        ConstructorImage::class
    ],
    version = 1
)
internal abstract class BoxBoxDatabase : RoomDatabase() {

    abstract fun driverStandingDao(): DriverStandingDao

    abstract fun fullDriverStandingDao(): FullDriverStandingDao

    abstract fun driverDao(): DriverDao

    abstract fun constructorDao(): ConstructorDao
}