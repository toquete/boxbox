package com.toquete.boxbox.di

import com.toquete.boxbox.core.common.util.Syncable
import com.toquete.boxbox.worker.repository.DefaultSyncRepository
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
}
