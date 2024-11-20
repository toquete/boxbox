package com.toquete.boxbox.data.races.di

import com.toquete.boxbox.data.races.repository.DefaultRaceRepository
import com.toquete.boxbox.domain.repository.RaceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindsRaceRepository(
        defaultRaceRepository: DefaultRaceRepository
    ): RaceRepository
}
