package com.toquete.boxbox.di

import com.toquete.boxbox.util.monitor.ConnectivityManagerNetworkMonitor
import com.toquete.boxbox.util.monitor.NetworkMonitor
import com.toquete.boxbox.util.monitor.SyncMonitor
import com.toquete.boxbox.util.monitor.WorkManagerSyncMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MonitorModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor

    @Binds
    fun bindsSyncMonitor(
        syncMonitor: WorkManagerSyncMonitor
    ): SyncMonitor
}
