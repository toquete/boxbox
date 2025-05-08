package com.toquete.boxbox.data.constructorimages.di

import com.toquete.boxbox.data.constructorimages.repository.DefaultConstructorImageRepository
import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import com.toquete.boxbox.data.constructorimages.source.remote.DefaultConstructorImageRemoteDataSource
import com.toquete.boxbox.domain.repository.ConstructorImageRepository
import org.koin.dsl.module

val constructorImageDataModule = module {
    single<ConstructorImageRemoteDataSource> { DefaultConstructorImageRemoteDataSource(remoteDatabase = get()) }
    single<ConstructorImageRepository> {
        DefaultConstructorImageRepository(
            remoteDataSource = get(),
            constructorImageDao = get()
        )
    }
}
