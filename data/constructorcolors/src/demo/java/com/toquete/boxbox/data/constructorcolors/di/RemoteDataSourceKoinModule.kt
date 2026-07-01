package com.toquete.boxbox.data.constructorcolors.di

import com.toquete.boxbox.data.constructorcolors.source.remote.ConstructorColorRemoteDataSource
import com.toquete.boxbox.data.constructorcolors.source.remote.DefaultConstructorColorRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val remoteDataSourceModule = module {
    singleOf(::DefaultConstructorColorRemoteDataSource) bind ConstructorColorRemoteDataSource::class
}
