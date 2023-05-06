package com.toquete.boxbox.data.fullconstructorstandings.di

import com.toquete.boxbox.data.fullconstructorstandings.source.local.DefaultFullConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.fullconstructorstandings.source.local.FullConstructorStandingsLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindsFullConstructorStandingsLocalDataSource(
        dataSourceImpl: DefaultFullConstructorStandingsLocalDataSource
    ): FullConstructorStandingsLocalDataSource
}
