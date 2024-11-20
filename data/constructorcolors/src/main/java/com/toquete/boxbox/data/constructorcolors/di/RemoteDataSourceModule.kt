package com.toquete.boxbox.data.constructorcolors.di

import com.toquete.boxbox.data.constructorcolors.source.remote.ConstructorColorRemoteDataSource
import com.toquete.boxbox.data.constructorcolors.source.remote.DefaultConstructorColorRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataSourceModule {

    @Binds
    fun bindsConstructorColorRemoteDataSource(
        remoteDataSource: DefaultConstructorColorRemoteDataSource
    ): ConstructorColorRemoteDataSource
}
