package com.toquete.boxbox.di

import com.toquete.boxbox.util.SyncMonitor
import com.toquete.boxbox.util.WorkManagerSyncMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SyncMonitorModule {

    @Binds
    fun bindsSyncMonitor(
        syncMonitor: WorkManagerSyncMonitor
    ): SyncMonitor
}
