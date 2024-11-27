package com.toquete.boxbox.di

import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.worker.repository.DefaultSyncRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
fun interface SyncRepositoryModule {

    @Binds
    fun bindsSyncRepository(
        repository: DefaultSyncRepository
    ): SyncRepository
}
