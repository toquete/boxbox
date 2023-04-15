package com.toquete.boxbox.data.constructorstandings.di

import com.toquete.boxbox.data.constructorstandings.source.remote.ConstructorStandingsRemoteDataSource
import com.toquete.boxbox.data.constructorstandings.source.remote.ConstructorStandingsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindsConstructorStandingsRemoteDataSource(
        dataSourceImpl: ConstructorStandingsRemoteDataSourceImpl
    ): ConstructorStandingsRemoteDataSource
}