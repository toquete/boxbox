package com.toquete.boxbox.data.driverimages.di

import com.toquete.boxbox.data.driverimages.source.remote.DefaultDriverImageRemoteDataSource
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal fun interface RemoteDataSourceModule {

    @Binds
    fun bindsDriverImageRemoteDataSource(
        dataSource: DefaultDriverImageRemoteDataSource
    ): DriverImageRemoteDataSource
}
