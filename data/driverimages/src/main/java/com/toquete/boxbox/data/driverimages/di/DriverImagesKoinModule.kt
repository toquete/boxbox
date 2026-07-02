package com.toquete.boxbox.data.driverimages.di

import com.toquete.boxbox.data.driverimages.repository.DefaultDriverImageRepository
import com.toquete.boxbox.domain.repository.DriverImageRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val driverImagesModule = module {
    includes(remoteDataSourceModule)
    singleOf(::DefaultDriverImageRepository) bind DriverImageRepository::class
}
