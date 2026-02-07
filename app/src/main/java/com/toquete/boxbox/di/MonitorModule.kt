package com.toquete.boxbox.di

import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.util.monitor.ConnectivityManagerNetworkMonitor
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
}
