package com.toquete.boxbox.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toquete.boxbox.database.dao.ConstructorDao
import com.toquete.boxbox.database.dao.ConstructorStandingDao
import com.toquete.boxbox.database.dao.DriverDao
import com.toquete.boxbox.database.dao.DriverStandingDao
import com.toquete.boxbox.database.dao.FullConstructorStandingDao
import com.toquete.boxbox.database.dao.FullDriverStandingDao
import com.toquete.boxbox.database.model.ConstructorEntity
import com.toquete.boxbox.database.model.ConstructorImage
import com.toquete.boxbox.database.model.ConstructorStandingEntity
import com.toquete.boxbox.database.model.CountryEntity
import com.toquete.boxbox.database.model.DriverEntity
import com.toquete.boxbox.database.model.DriverImageEntity
import com.toquete.boxbox.database.model.DriverStandingEntity

@Database(
    entities = [
        DriverStandingEntity::class,
        DriverEntity::class,
        ConstructorStandingEntity::class,
        ConstructorEntity::class,
        CountryEntity::class,
        DriverImageEntity::class,
        ConstructorImage::class
    ],
    version = 1
)
internal abstract class BoxBoxDatabase : RoomDatabase() {

    abstract fun driverStandingDao(): DriverStandingDao

    abstract fun fullDriverStandingDao(): FullDriverStandingDao

    abstract fun driverDao(): DriverDao

    abstract fun constructorDao(): ConstructorDao

    abstract fun constructorStandingDao(): ConstructorStandingDao

    abstract fun fullConstructorStandingDao(): FullConstructorStandingDao
}