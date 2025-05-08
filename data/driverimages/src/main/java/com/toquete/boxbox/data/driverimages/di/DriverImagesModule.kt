package com.toquete.boxbox.data.driverimages.di

import com.toquete.boxbox.data.driverimages.repository.DefaultDriverImageRepository
import com.toquete.boxbox.data.driverimages.source.remote.DefaultDriverImageRemoteDataSource
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource
import com.toquete.boxbox.domain.repository.DriverImageRepository
import org.koin.dsl.module

val driverImagesModule = module {
    single<DriverImageRemoteDataSource> { DefaultDriverImageRemoteDataSource(remoteDatabase = get()) }
    single<DriverImageRepository> {
        DefaultDriverImageRepository(
            remoteDataSource = get(),
            driverImageDao = get()
        )
    }
}
