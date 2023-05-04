package com.toquete.boxbox.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.dao.FullConstructorStandingDao
import com.toquete.boxbox.core.database.dao.FullDriverStandingDao
import com.toquete.boxbox.core.database.model.ConstructorEntity
import com.toquete.boxbox.core.database.model.ConstructorImage
import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import com.toquete.boxbox.core.database.model.CountryEntity
import com.toquete.boxbox.core.database.model.DriverEntity
import com.toquete.boxbox.core.database.model.DriverImageEntity
import com.toquete.boxbox.core.database.model.DriverStandingEntity

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
