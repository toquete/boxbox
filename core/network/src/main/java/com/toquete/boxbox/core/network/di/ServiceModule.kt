package com.toquete.boxbox.core.network.di

import com.toquete.boxbox.core.network.BoxBoxService
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
    fun providesBoxBoxService(
        retrofit: Retrofit
    ): BoxBoxService {
        return retrofit.create(BoxBoxService::class.java)
    }
}
