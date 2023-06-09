package com.toquete.boxbox.data.driverimages.di

import com.toquete.boxbox.data.driverimages.repository.DefaultDriverImageRepository
import com.toquete.boxbox.data.driverimages.repository.DriverImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsDriverImageRepository(
        repository: DefaultDriverImageRepository
    ): DriverImageRepository
}
