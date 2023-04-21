package com.toquete.boxbox.data.fullconstructorstandings.di

import com.toquete.boxbox.data.fullconstructorstandings.source.local.FullConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.fullconstructorstandings.source.local.FullConstructorStandingsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSource {

    @Binds
    abstract fun bindsFullConstructorStandingsLocalDataSource(
        dataSourceImpl: FullConstructorStandingsLocalDataSourceImpl
    ): FullConstructorStandingsLocalDataSource
}