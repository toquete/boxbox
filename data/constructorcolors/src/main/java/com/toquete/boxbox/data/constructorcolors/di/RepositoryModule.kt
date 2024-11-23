package com.toquete.boxbox.data.constructorcolors.di

import com.toquete.boxbox.data.constructorcolors.repository.DefaultConstructorColorRepository
import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal fun interface RepositoryModule {

    @Binds
    @Named("internal")
    fun bindConstructorColorRepository(
        defaultConstructorColorRepository: DefaultConstructorColorRepository
    ): ConstructorColorRepository
}
