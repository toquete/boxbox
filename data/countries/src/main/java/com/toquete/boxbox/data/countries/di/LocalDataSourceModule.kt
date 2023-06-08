package com.toquete.boxbox.data.countries.di

import com.toquete.boxbox.data.countries.source.local.CountryLocalDataSource
import com.toquete.boxbox.data.countries.source.local.DefaultCountryLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindsCountryLocalDataSource(
        dataSourceImpl: DefaultCountryLocalDataSource
    ): CountryLocalDataSource
}
