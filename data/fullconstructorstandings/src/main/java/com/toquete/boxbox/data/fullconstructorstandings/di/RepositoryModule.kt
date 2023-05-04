package com.toquete.boxbox.data.fullconstructorstandings.di

import com.toquete.boxbox.data.fullconstructorstandings.repository.DefaultFullConstructorStandingsRepository
import com.toquete.boxbox.data.fullconstructorstandings.repository.FullConstructorStandingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsFullConstructorStandingsRepository(
        repositoryImpl: DefaultFullConstructorStandingsRepository
    ): FullConstructorStandingsRepository
}
