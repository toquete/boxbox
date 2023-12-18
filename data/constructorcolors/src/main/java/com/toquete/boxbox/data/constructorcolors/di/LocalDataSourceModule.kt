package com.toquete.boxbox.data.constructorcolors.di

import com.toquete.boxbox.data.constructorcolors.source.local.ConstructorColorLocalDataSource
import com.toquete.boxbox.data.constructorcolors.source.local.DefaultConstructorColorLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindConstructorColorLocalDataSource(
        defaultConstructorColorLocalDataSource: DefaultConstructorColorLocalDataSource
    ): ConstructorColorLocalDataSource
}
