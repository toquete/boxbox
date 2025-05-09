package com.toquete.boxbox.data.driverimages.di

import com.toquete.boxbox.data.driverimages.repository.DefaultDriverImageRepository
import com.toquete.boxbox.data.driverimages.source.remote.DefaultDriverImageRemoteDataSource
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource
import com.toquete.boxbox.domain.repository.DriverImageRepository
import org.koin.dsl.module

val driverImagesDataModule = module {
    single<DriverImageRemoteDataSource> { DefaultDriverImageRemoteDataSource() }
    single<DriverImageRepository> {
        DefaultDriverImageRepository(
            remoteDataSource = get(),
            driverImageDao = get()
        )
    }
}
