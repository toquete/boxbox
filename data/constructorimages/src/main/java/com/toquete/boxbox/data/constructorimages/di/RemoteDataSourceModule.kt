package com.toquete.boxbox.data.constructorimages.di

import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import com.toquete.boxbox.data.constructorimages.source.remote.DefaultConstructorImageRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataSourceModule {

    @Binds
    fun bindsConstructorImageRemoteDataSource(
        dataSource: DefaultConstructorImageRemoteDataSource
    ): ConstructorImageRemoteDataSource
}
