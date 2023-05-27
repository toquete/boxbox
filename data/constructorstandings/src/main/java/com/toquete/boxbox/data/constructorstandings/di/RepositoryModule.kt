package com.toquete.boxbox.data.constructorstandings.di

import com.toquete.boxbox.data.constructorstandings.repository.ConstructorStandingsRepository
import com.toquete.boxbox.data.constructorstandings.repository.DefaultConstructorStandingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsFullConstructorStandingsRepository(
        repositoryImpl: DefaultConstructorStandingsRepository
    ): ConstructorStandingsRepository
}
