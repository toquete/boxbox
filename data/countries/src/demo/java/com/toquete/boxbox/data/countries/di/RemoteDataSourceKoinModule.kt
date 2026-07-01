package com.toquete.boxbox.data.countries.di

import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource
import com.toquete.boxbox.data.countries.source.remote.DefaultCountryRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val remoteDataSourceModule = module {
    singleOf(::DefaultCountryRemoteDataSource) bind CountryRemoteDataSource::class
}
