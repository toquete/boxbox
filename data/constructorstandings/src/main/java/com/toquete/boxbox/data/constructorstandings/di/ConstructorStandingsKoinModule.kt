package com.toquete.boxbox.data.constructorstandings.di

import com.toquete.boxbox.data.constructorstandings.repository.DefaultConstructorStandingsRepository
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val constructorStandingsModule = module {
    includes(remoteDataSourceModule)
    singleOf(::DefaultConstructorStandingsRepository) bind ConstructorStandingsRepository::class
}
