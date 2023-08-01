package com.toquete.boxbox.di

import com.toquete.boxbox.core.common.util.Syncable
import com.toquete.boxbox.worker.repository.DefaultImagesRepository
import com.toquete.boxbox.worker.repository.DefaultStandingsRepository
import com.toquete.boxbox.worker.repository.DefaultSyncRepository
import com.toquete.boxbox.worker.repository.ImagesRepository
import com.toquete.boxbox.worker.repository.StandingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SyncRepositoryModule {

    @Binds
    abstract fun bindsSyncRepository(
        repository: DefaultSyncRepository
    ): Syncable

    @Binds
    abstract fun bindsStandingsRepository(
        repository: DefaultStandingsRepository
    ): StandingsRepository

    @Binds
    abstract fun bindsImagesRepository(
        repository: DefaultImagesRepository
    ): ImagesRepository
}
