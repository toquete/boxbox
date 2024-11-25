package com.toquete.boxbox.data.constructorstandings.di

import com.toquete.boxbox.data.constructorstandings.repository.DefaultConstructorStandingsRepository
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
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
    fun bindsFullConstructorStandingsRepository(
        repositoryImpl: DefaultConstructorStandingsRepository
    ): ConstructorStandingsRepository
}
