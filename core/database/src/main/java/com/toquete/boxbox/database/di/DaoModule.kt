package com.toquete.boxbox.database.di

import com.toquete.boxbox.database.BoxBoxDatabase
import com.toquete.boxbox.database.dao.ConstructorDao
import com.toquete.boxbox.database.dao.DriverDao
import com.toquete.boxbox.database.dao.DriverStandingDao
import com.toquete.boxbox.database.dao.FullDriverStandingDao
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
}