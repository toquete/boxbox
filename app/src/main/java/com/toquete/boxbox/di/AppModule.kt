package com.toquete.boxbox.di

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.core.common.util.SyncMonitor
import com.toquete.boxbox.core.database.di.databaseModule
import com.toquete.boxbox.core.network.di.networkModule
import com.toquete.boxbox.core.notification.di.notificationModule
import com.toquete.boxbox.core.preferences.di.preferencesModule
import com.toquete.boxbox.data.circuitimages.di.circuitImageDataModule
import com.toquete.boxbox.data.constructorcolors.di.constructorColorDataModule
import com.toquete.boxbox.data.constructorimages.di.constructorImageDataModule
import com.toquete.boxbox.data.constructorstandings.di.constructorStandingsDataModule
import com.toquete.boxbox.data.countries.di.countriesDataModule
import com.toquete.boxbox.data.driverimages.di.driverImagesDataModule
import com.toquete.boxbox.data.driverstandings.di.driverStandingsDataModule
import com.toquete.boxbox.data.raceresults.di.raceResultsDataModule
import com.toquete.boxbox.data.races.di.racesDataModule
import com.toquete.boxbox.data.sprintresults.di.sprintResultsDataModule
import com.toquete.boxbox.domain.di.domainModule
import com.toquete.boxbox.domain.repository.RemoteConfigRepository
import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.feature.home.di.homeFeatureModule
import com.toquete.boxbox.feature.raceresults.di.raceResultsFeatureModule
import com.toquete.boxbox.feature.races.di.racesFeatureModule
import com.toquete.boxbox.feature.settings.di.settingsFeatureModule
import com.toquete.boxbox.feature.standings.di.standingsFeatureModule
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

val prodModule = module {
    includes(
        appModule,
        buildVariantModule,
        homeFeatureModule,
        raceResultsFeatureModule,
        racesFeatureModule,
        settingsFeatureModule,
        standingsFeatureModule,
        domainModule,
        circuitImageDataModule,
        constructorColorDataModule,
        constructorImageDataModule,
        constructorStandingsDataModule,
        countriesDataModule,
        driverImagesDataModule,
        driverStandingsDataModule,
        raceResultsDataModule,
        racesDataModule,
        sprintResultsDataModule,
        databaseModule,
        networkModule,
        notificationModule,
        preferencesModule
    )
}
