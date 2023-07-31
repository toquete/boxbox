package com.toquete.boxbox.data.races.di

import com.toquete.boxbox.data.races.repository.DefaultRaceRepository
import com.toquete.boxbox.data.races.repository.RaceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsRaceRepository(
        defaultRaceRepository: DefaultRaceRepository
    ): RaceRepository
}
