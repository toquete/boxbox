package com.toquete.boxbox.data.constructorimages.di

import com.toquete.boxbox.data.constructorimages.repository.DefaultConstructorImageRepository
import com.toquete.boxbox.domain.repository.ConstructorImageRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val constructorImagesModule = module {
    includes(remoteDataSourceModule)
    singleOf(::DefaultConstructorImageRepository) bind ConstructorImageRepository::class
}
