package com.toquete.boxbox.data.driverimages.di

import com.toquete.boxbox.data.driverimages.source.remote.DefaultDriverImageRemoteDataSource
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val remoteDataSourceModule = module {
    singleOf(::DefaultDriverImageRemoteDataSource) bind DriverImageRemoteDataSource::class
}
