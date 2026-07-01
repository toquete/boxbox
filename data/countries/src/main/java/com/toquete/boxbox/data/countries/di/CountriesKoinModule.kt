package com.toquete.boxbox.data.countries.di

import com.toquete.boxbox.data.countries.repository.DefaultCountryRepository
import com.toquete.boxbox.domain.repository.CountryRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val countriesModule = module {
    includes(remoteDataSourceModule)
    singleOf(::DefaultCountryRepository) bind CountryRepository::class
}
