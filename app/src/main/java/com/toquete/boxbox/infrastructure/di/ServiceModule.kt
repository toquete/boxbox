package com.toquete.boxbox.infrastructure.di

import com.toquete.boxbox.data.source.remote.service.DriverStandingsService
import com.toquete.boxbox.data.source.remote.service.ServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun providesService(): DriverStandingsService {
        return ServiceFactory.create(DriverStandingsService::class.java)
    }
}