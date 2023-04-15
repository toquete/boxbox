package com.toquete.boxbox.network.di

import com.toquete.boxbox.network.DriverStandingsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Singleton
    @Provides
    fun providesDriverStandingsService(
        retrofit: Retrofit
    ): DriverStandingsService {
        return retrofit.create(DriverStandingsService::class.java)
    }
}