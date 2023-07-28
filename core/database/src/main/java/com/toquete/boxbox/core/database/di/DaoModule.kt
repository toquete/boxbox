package com.toquete.boxbox.core.database.di

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.dao.CircuitDao
import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.dao.RaceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
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
}
