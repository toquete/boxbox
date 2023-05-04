package com.toquete.boxbox.data.constructorstandings.di

import com.toquete.boxbox.data.constructorstandings.source.local.ConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.constructorstandings.source.local.DefaultConstructorStandingsLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSource {

    @Binds
    abstract fun bindsConstructorStandingsLocalDataSource(
        dataSourceImpl: DefaultConstructorStandingsLocalDataSource
    ): ConstructorStandingsLocalDataSource
}
