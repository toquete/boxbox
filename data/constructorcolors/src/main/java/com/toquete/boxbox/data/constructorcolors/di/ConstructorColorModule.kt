package com.toquete.boxbox.data.constructorcolors.di

import com.toquete.boxbox.data.constructorcolors.repository.DefaultConstructorColorRepository
import com.toquete.boxbox.data.constructorcolors.source.remote.ConstructorColorRemoteDataSource
import com.toquete.boxbox.data.constructorcolors.source.remote.DefaultConstructorColorRemoteDataSource
import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import org.koin.dsl.module

val constructorColorModule = module {
    single<ConstructorColorRemoteDataSource> { DefaultConstructorColorRemoteDataSource(remoteDatabase = get()) }
    single<ConstructorColorRepository> {
        DefaultConstructorColorRepository(
            remoteDataSource = get(),
            constructorColorDao = get()
        )
    }
}
