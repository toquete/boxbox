package com.toquete.boxbox.data.raceresults.di

import com.toquete.boxbox.data.raceresults.repository.DefaultRaceResultRepository
import com.toquete.boxbox.domain.repository.RaceResultRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal fun interface RepositoryModule {

    @Binds
    fun bindsRaceRepository(
        defaultRaceResultRepository: DefaultRaceResultRepository
    ): RaceResultRepository
}
