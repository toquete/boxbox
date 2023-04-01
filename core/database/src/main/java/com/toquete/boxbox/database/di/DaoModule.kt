package com.toquete.boxbox.database.di

import com.toquete.boxbox.database.BoxBoxDatabase
import com.toquete.boxbox.database.dao.DriverStandingDao
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
}