package com.toquete.boxbox.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.model.ConstructorEntity
import com.toquete.boxbox.core.database.model.ConstructorImageEntity
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
        ConstructorImageEntity::class
    ],
    version = 1
)
internal abstract class BoxBoxDatabase : RoomDatabase() {

    abstract fun driverStandingDao(): DriverStandingDao

    abstract fun driverDao(): DriverDao

    abstract fun constructorDao(): ConstructorDao

    abstract fun constructorStandingDao(): ConstructorStandingDao

    abstract fun constructorImageDao(): ConstructorImageDao

    abstract fun driverImageDao(): DriverImageDao

    abstract fun countryDao(): CountryDao
}
