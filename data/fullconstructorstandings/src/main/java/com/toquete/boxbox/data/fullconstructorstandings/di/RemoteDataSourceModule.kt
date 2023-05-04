package com.toquete.boxbox.data.fullconstructorstandings.di

import com.toquete.boxbox.data.fullconstructorstandings.source.remote.DefaultFullConstructorStandingsRemoteDataSource
import com.toquete.boxbox.data.fullconstructorstandings.source.remote.FullConstructorStandingsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindsFullConstructorStandingsRemoteDataSource(
        dataSourceImpl: DefaultFullConstructorStandingsRemoteDataSource
    ): FullConstructorStandingsRemoteDataSource
}
