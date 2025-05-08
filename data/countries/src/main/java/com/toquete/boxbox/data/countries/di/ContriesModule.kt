package com.toquete.boxbox.data.countries.di

import com.toquete.boxbox.data.countries.repository.DefaultCountryRepository
import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource
import com.toquete.boxbox.data.countries.source.remote.DefaultCountryRemoteDataSource
import com.toquete.boxbox.domain.repository.CountryRepository
import org.koin.dsl.module

val countriesDataModule = module {
    single<CountryRemoteDataSource> { DefaultCountryRemoteDataSource(remoteDatabase = get()) }
    single<CountryRepository> {
        DefaultCountryRepository(
            remoteDataSource = get(),
            countryDao = get()
        )
    }
}
