package com.toquete.boxbox.data.constructorcolors.di

import com.toquete.boxbox.data.constructorcolors.repository.DefaultConstructorColorRepository
import com.toquete.boxbox.data.constructorcolors.source.remote.ConstructorColorRemoteDataSource
import com.toquete.boxbox.data.constructorcolors.source.remote.DefaultConstructorColorRemoteDataSource
import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val constructorColorsModule = module {
    singleOf(::DefaultConstructorColorRemoteDataSource) bind ConstructorColorRemoteDataSource::class
    singleOf(::DefaultConstructorColorRepository) bind ConstructorColorRepository::class
}
