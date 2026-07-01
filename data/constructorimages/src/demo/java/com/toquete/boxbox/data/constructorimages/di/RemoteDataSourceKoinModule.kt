package com.toquete.boxbox.data.constructorimages.di

import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import com.toquete.boxbox.data.constructorimages.source.remote.DefaultConstructorImageRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val remoteDataSourceModule = module {
    singleOf(::DefaultConstructorImageRemoteDataSource) bind ConstructorImageRemoteDataSource::class
}
