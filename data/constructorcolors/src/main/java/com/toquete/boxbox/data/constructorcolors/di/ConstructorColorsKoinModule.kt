package com.toquete.boxbox.data.constructorcolors.di

import com.toquete.boxbox.data.constructorcolors.repository.DefaultConstructorColorRepository
import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val constructorColorsModule = module {
    includes(remoteDataSourceModule)
    singleOf(::DefaultConstructorColorRepository) bind ConstructorColorRepository::class
}
