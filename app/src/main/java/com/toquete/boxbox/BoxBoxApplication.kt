package com.toquete.boxbox

import android.app.Application
import androidx.core.content.edit
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.google.android.gms.ads.MobileAds
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.appcheck.AppCheckProviderFactory
import com.google.firebase.appcheck.appCheck
import com.google.firebase.initialize
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.toquete.boxbox.core.database.di.databaseModule
import com.toquete.boxbox.core.network.di.networkModule
import com.toquete.boxbox.core.notification.di.notificationModule
import com.toquete.boxbox.core.preferences.di.preferencesModule
import com.toquete.boxbox.data.circuitimages.di.circuitImageDataModule
import com.toquete.boxbox.data.constructorcolors.di.constructorColorDataModule
import com.toquete.boxbox.data.constructorimages.di.constructorImageDataModule
import com.toquete.boxbox.data.constructorstandings.di.constructorStandingsDataModule
import com.toquete.boxbox.data.countries.di.countriesModule
import com.toquete.boxbox.data.driverimages.di.driverImagesModule
import com.toquete.boxbox.data.driverstandings.di.driverStandingsModule
import com.toquete.boxbox.data.raceresults.di.raceResultsModule
import com.toquete.boxbox.data.races.di.racesModule
import com.toquete.boxbox.data.sprintresults.di.sprintResultsModule
import com.toquete.boxbox.di.appModule
import com.toquete.boxbox.di.buildVariantModule
import com.toquete.boxbox.domain.di.domainModule
import com.toquete.boxbox.feature.home.di.homeModule
import com.toquete.boxbox.feature.raceresults.di.raceResultModule
import com.toquete.boxbox.feature.races.di.racesFeatureModule
import com.toquete.boxbox.feature.settings.di.settingsModule
import com.toquete.boxbox.feature.standings.di.standingsModule
import com.toquete.boxbox.util.remoteconfig.remoteConfigDefaults
import com.toquete.boxbox.worker.SyncWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import timber.log.Timber

private const val APP_CHECK_DEBUG_STORE = "com.google.firebase.appcheck.debug.store.%s"
private const val APP_CHECK_DEBUG_TOKEN_KEY = "com.google.firebase.appcheck.debug.DEBUG_SECRET"
private const val MEMORY_CACHE_PERCENT = 0.1
private const val DISK_CACHE_PERCENT = 0.03
private const val MINIMUM_REMOTE_CONFIG_FETCH_INTERVAL = 0L
const val SYNC_WORK_NAME = "SYNC_WORK_NAME"

class BoxBoxApplication : Application(), KoinComponent, ImageLoaderFactory {

    private val timberTree: Timber.Tree by inject()
    private val appCheckProviderFactory: AppCheckProviderFactory by inject()

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupAppCheck()
        setupSyncWork()
        setupTimber()
        setupMobileAds()
        setupRemoteConfig()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(MEMORY_CACHE_PERCENT)
                    .strongReferencesEnabled(enable = true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(DISK_CACHE_PERCENT)
                    .directory(cacheDir)
                    .build()
            }
            .respectCacheHeaders(enable = false)
            .networkObserverEnabled(enable = true)
            .build()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@BoxBoxApplication)
            modules(
                appModule,
                buildVariantModule,
                homeModule,
                raceResultModule,
                racesFeatureModule,
                settingsModule,
                standingsModule,
                domainModule,
                circuitImageDataModule,
                constructorColorDataModule,
                constructorImageDataModule,
                constructorStandingsDataModule,
                countriesModule,
                driverImagesModule,
                driverStandingsModule,
                raceResultsModule,
                racesModule,
                sprintResultsModule,
                databaseModule,
                networkModule,
                notificationModule,
                preferencesModule
            )
            workManagerFactory()
        }
    }

    private fun setupSyncWork() {
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                SYNC_WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                SyncWorker.setupWork()
            )
    }

    private fun setupTimber() {
        Timber.plant(timberTree)
    }

    private fun setupAppCheck() {
        val persistenceKey = FirebaseApp.getPersistenceKey(
            FirebaseApp.DEFAULT_APP_NAME,
            FirebaseOptions.fromResource(this)
        )
        val prefsName = APP_CHECK_DEBUG_STORE.format(persistenceKey)
        getSharedPreferences(prefsName, MODE_PRIVATE).edit {
            putString(APP_CHECK_DEBUG_TOKEN_KEY, BuildConfig.APP_CHECK_DEBUG_TOKEN)
        }
        Firebase.initialize(this)
        Firebase.appCheck.installAppCheckProviderFactory(appCheckProviderFactory)
    }

    private fun setupMobileAds() {
        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(this@BoxBoxApplication)
        }
    }

    private fun setupRemoteConfig() {
        Firebase.remoteConfig.apply {
            if (BuildConfig.DEBUG) {
                val configSettings = remoteConfigSettings {
                    minimumFetchIntervalInSeconds = MINIMUM_REMOTE_CONFIG_FETCH_INTERVAL
                }
                setConfigSettingsAsync(configSettings)
            }
            setDefaultsAsync(remoteConfigDefaults)
            addOnConfigUpdateListener(object : ConfigUpdateListener {
                override fun onUpdate(configUpdate: ConfigUpdate) {
                    activate().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Timber.d("Remote config activated")
                        } else {
                            Timber.e(task.exception, "Failed to activate remote config")
                        }
                    }
                }

                override fun onError(error: FirebaseRemoteConfigException) {
                    Timber.e(error, "Failed to update remote config")
                }
            })
        }
    }
}
