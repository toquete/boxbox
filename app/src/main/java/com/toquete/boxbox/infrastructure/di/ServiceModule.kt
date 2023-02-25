package com.toquete.boxbox.infrastructure.di

import com.toquete.boxbox.data.source.remote.service.DriverStandingsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun providesDriverStandingsService(
        retrofit: Retrofit
    ): DriverStandingsService {
        return retrofit.create(DriverStandingsService::class.java)
    }
}