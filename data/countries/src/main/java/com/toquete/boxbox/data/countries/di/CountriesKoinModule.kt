package com.toquete.boxbox.data.countries.di

import com.toquete.boxbox.data.countries.repository.DefaultCountryRepository
import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource
import com.toquete.boxbox.data.countries.source.remote.DefaultCountryRemoteDataSource
import com.toquete.boxbox.domain.repository.CountryRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val countriesModule = module {
    singleOf(::DefaultCountryRemoteDataSource) bind CountryRemoteDataSource::class
    singleOf(::DefaultCountryRepository) bind CountryRepository::class
}
