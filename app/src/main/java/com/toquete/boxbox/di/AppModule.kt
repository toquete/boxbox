package com.toquete.boxbox.di

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.core.common.util.SyncMonitor
import com.toquete.boxbox.domain.repository.RemoteConfigRepository
import com.toquete.boxbox.util.monitor.ConnectivityManagerNetworkMonitor
import com.toquete.boxbox.util.monitor.WorkManagerSyncMonitor
import com.toquete.boxbox.util.remoteconfig.FirebaseRemoteConfigRepository
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { Firebase.analytics }
    single { Firebase.crashlytics }
    single { Firebase.remoteConfig }
    single<RemoteConfigRepository> {
        FirebaseRemoteConfigRepository(
            firebaseRemoteConfig = get(),
            dispatcher = Dispatchers.IO
        )
    }
    single<RemoteConfigRepository> {
        FirebaseRemoteConfigRepository(
            firebaseRemoteConfig = get(),
            dispatcher = Dispatchers.IO
        )
    }
    single<NetworkMonitor> { ConnectivityManagerNetworkMonitor(context = androidApplication()) }
    single<SyncMonitor> { WorkManagerSyncMonitor(context = androidApplication()) }
}
