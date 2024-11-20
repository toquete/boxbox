package com.toquete.boxbox.data.countries.di

import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource
import com.toquete.boxbox.data.countries.source.remote.DefaultCountryRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataSourceModule {

    @Binds
    fun bindsCountryRemoteDataSource(
        dataSourceImpl: DefaultCountryRemoteDataSource
    ): CountryRemoteDataSource
}
