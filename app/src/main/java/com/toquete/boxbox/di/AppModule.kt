package com.toquete.boxbox.di

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.core.common.util.SyncMonitor
import com.toquete.boxbox.domain.repository.RemoteConfigRepository
import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.ui.MainViewModel
import com.toquete.boxbox.util.monitor.ConnectivityManagerNetworkMonitor
import com.toquete.boxbox.util.monitor.WorkManagerSyncMonitor
import com.toquete.boxbox.util.remoteconfig.FirebaseRemoteConfigRepository
import com.toquete.boxbox.worker.SyncWorker
import com.toquete.boxbox.worker.repository.DefaultSyncRepository
import com.toquete.boxbox.worker.repository.ImagesRepository
import com.toquete.boxbox.worker.repository.StandingsRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { Firebase.analytics }
    single { Firebase.crashlytics }
    single { Firebase.remoteConfig }
    single<RemoteConfigRepository> {
        FirebaseRemoteConfigRepository(
            firebaseRemoteConfig = get()
        )
    }
    single<SyncRepository> {
        DefaultSyncRepository(
            standingsRepository = get(),
            imagesRepository = get(),
            raceRepository = get(),
            userPreferencesRepository = get(),
            clock = get()
        )
    }
    singleOf(::ImagesRepository)
    singleOf(::StandingsRepository)
    single<NetworkMonitor> { ConnectivityManagerNetworkMonitor(context = androidApplication()) }
    single<SyncMonitor> { WorkManagerSyncMonitor(context = androidApplication()) }
    workerOf(::SyncWorker)
    viewModelOf(::MainViewModel)
}
