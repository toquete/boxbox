package com.toquete.boxbox.data.constructorimages.di

import com.toquete.boxbox.data.constructorimages.repository.DefaultConstructorImageRepository
import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import com.toquete.boxbox.data.constructorimages.source.remote.DefaultConstructorImageRemoteDataSource
import com.toquete.boxbox.domain.repository.ConstructorImageRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val constructorImagesModule = module {
    singleOf(::DefaultConstructorImageRemoteDataSource) bind ConstructorImageRemoteDataSource::class
    singleOf(::DefaultConstructorImageRepository) bind ConstructorImageRepository::class
}
