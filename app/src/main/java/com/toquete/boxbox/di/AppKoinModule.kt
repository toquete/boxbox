package com.toquete.boxbox.di

import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.domain.repository.RemoteConfigRepository
import com.toquete.boxbox.ui.HomeViewModel
import com.toquete.boxbox.ui.MainViewModel
import com.toquete.boxbox.util.monitor.ConnectivityManagerNetworkMonitor
import com.toquete.boxbox.util.remoteconfig.FirebaseRemoteConfigRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single<FirebaseRemoteConfig> { Firebase.remoteConfig }
    single<FirebaseAnalytics> { Firebase.analytics }
    single<FirebaseCrashlytics> { Firebase.crashlytics }

    single<NetworkMonitor> { ConnectivityManagerNetworkMonitor(androidContext()) }

    singleOf(::FirebaseRemoteConfigRepository) bind RemoteConfigRepository::class

    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
}
