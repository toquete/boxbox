package com.toquete.boxbox.data.constructorimages.source.di

import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import com.toquete.boxbox.data.constructorimages.source.remote.DefaultConstructorImageRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindsConstructorImageRemoteDataSource(
        dataSource: DefaultConstructorImageRemoteDataSource
    ): ConstructorImageRemoteDataSource
}
