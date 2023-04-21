package com.toquete.boxbox.data.constructors.di

import com.toquete.boxbox.data.constructors.source.local.ConstructorsLocalDataSource
import com.toquete.boxbox.data.constructors.source.local.ConstructorsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindsConstructorsLocalDataSource(
        dataSourceImpl: ConstructorsLocalDataSourceImpl
    ): ConstructorsLocalDataSource
}