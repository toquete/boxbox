package com.toquete.boxbox.sync.di

import com.toquete.boxbox.core.common.util.SyncMonitor
import com.toquete.boxbox.sync.monitor.WorkManagerSyncMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
fun interface SyncMonitorModule {

    @Binds
    fun bindsSyncMonitor(
        syncMonitor: WorkManagerSyncMonitor
    ): SyncMonitor
}
