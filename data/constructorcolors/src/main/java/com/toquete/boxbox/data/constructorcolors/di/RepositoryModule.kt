package com.toquete.boxbox.data.constructorcolors.di

import com.toquete.boxbox.data.constructorcolors.repository.ConstructorColorRepository
import com.toquete.boxbox.data.constructorcolors.repository.DefaultConstructorColorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindConstructorColorRepository(
        defaultConstructorColorRepository: DefaultConstructorColorRepository
    ): ConstructorColorRepository
}
