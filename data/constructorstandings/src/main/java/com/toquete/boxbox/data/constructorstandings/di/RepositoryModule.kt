package com.toquete.boxbox.data.constructorstandings.di

import com.toquete.boxbox.data.constructorstandings.repository.DefaultConstructorStandingsRepository
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindsFullConstructorStandingsRepository(
        repositoryImpl: DefaultConstructorStandingsRepository
    ): ConstructorStandingsRepository
}
