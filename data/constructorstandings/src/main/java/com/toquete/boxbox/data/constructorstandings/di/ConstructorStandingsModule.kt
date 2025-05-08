package com.toquete.boxbox.data.constructorstandings.di

import com.toquete.boxbox.data.constructorstandings.repository.DefaultConstructorStandingsRepository
import com.toquete.boxbox.data.constructorstandings.source.remote.ConstructorStandingsRemoteDataSource
import com.toquete.boxbox.data.constructorstandings.source.remote.DefaultConstructorStandingsRemoteDataSource
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import org.koin.dsl.module

val constructorStandingsModule = module {
    single<ConstructorStandingsRemoteDataSource> { DefaultConstructorStandingsRemoteDataSource(service = get()) }
    single<ConstructorStandingsRepository> {
        DefaultConstructorStandingsRepository(
            remoteDataSource = get(),
            constructorStandingDao = get()
        )
    }
}
