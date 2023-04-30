package com.toquete.boxbox.core.database.di

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.dao.FullConstructorStandingDao
import com.toquete.boxbox.core.database.dao.FullDriverStandingDao
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
    fun provideFullDriverStandingDao(database: BoxBoxDatabase): FullDriverStandingDao {
        return database.fullDriverStandingDao()
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
    fun provideFullConstructorStandingDao(database: BoxBoxDatabase): FullConstructorStandingDao {
        return database.fullConstructorStandingDao()
    }
}