package com.toquete.boxbox.data.constructorstandings.di

import com.toquete.boxbox.data.constructorstandings.source.local.ConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.constructorstandings.source.local.ConstructorStandingsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSource {

    @Binds
    abstract fun bindsConstructorStandingsLocalDataSource(
        dataSourceImpl: ConstructorStandingsLocalDataSourceImpl
    ): ConstructorStandingsLocalDataSource
}