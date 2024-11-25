package com.toquete.boxbox.data.races.di

import com.toquete.boxbox.data.races.repository.DefaultRaceRepository
import com.toquete.boxbox.domain.repository.RaceRepository
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
    fun bindsRaceRepository(
        defaultRaceRepository: DefaultRaceRepository
    ): RaceRepository
}
