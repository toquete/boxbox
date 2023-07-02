package com.toquete.boxbox.data.constructorimages.di

import com.toquete.boxbox.data.constructorimages.source.local.ConstructorImageLocalDataSource
import com.toquete.boxbox.data.constructorimages.source.local.DefaultConstructorImageLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindsConstructorImageLocalDataSource(
        dataSource: DefaultConstructorImageLocalDataSource
    ): ConstructorImageLocalDataSource
}
