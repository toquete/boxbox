package com.toquete.boxbox.core.database.di

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.dao.CircuitDao
import com.toquete.boxbox.core.database.dao.CircuitImageDao
import com.toquete.boxbox.core.database.dao.ConstructorColorDao
import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.dao.RaceDao
import com.toquete.boxbox.core.database.dao.RaceResultDao
import com.toquete.boxbox.core.database.dao.SprintRaceResultDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
@Suppress("TooManyFunctions")
internal object DaoModule {

    @Provides
    fun provideDriverStandingDao(database: BoxBoxDatabase): DriverStandingDao {
        return database.driverStandingDao()
    }

    @Provides
    fun provideDriverDao(database: BoxBoxDatabase): DriverDao {
        return database.driverDao()
    }

    @Provides
    fun provideConstructorDao(database: BoxBoxDatabase): ConstructorDao {
        return database.constructorDao()
    }

    @Provides
    fun provideConstructorStandingDao(database: BoxBoxDatabase): ConstructorStandingDao {
        return database.constructorStandingDao()
    }

    @Provides
    fun providesCountryDao(database: BoxBoxDatabase): CountryDao {
        return database.countryDao()
    }

    @Provides
    fun providesDriverImageDao(database: BoxBoxDatabase): DriverImageDao {
        return database.driverImageDao()
    }

    @Provides
    fun providesConstructorImageDao(database: BoxBoxDatabase): ConstructorImageDao {
        return database.constructorImageDao()
    }

    @Provides
    fun providesRaceDao(database: BoxBoxDatabase): RaceDao {
        return database.raceDao()
    }

    @Provides
    fun providesCircuitDao(database: BoxBoxDatabase): CircuitDao {
        return database.circuitDao()
    }

    @Provides
    fun providesCircuitImageDao(database: BoxBoxDatabase): CircuitImageDao {
        return database.circuitImageDao()
    }

    @Provides
    fun providesConstructorColorDao(database: BoxBoxDatabase): ConstructorColorDao {
        return database.constructorColorDao()
    }

    @Provides
    fun providesRaceResultDao(database: BoxBoxDatabase): RaceResultDao {
        return database.raceResultDao()
    }

    @Provides
    fun providesSprintRaceResultDao(database: BoxBoxDatabase): SprintRaceResultDao {
        return database.sprintRaceResultDao()
    }
}
