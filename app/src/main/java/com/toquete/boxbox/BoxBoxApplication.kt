package com.toquete.boxbox

import android.app.Application
import androidx.core.content.edit
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
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
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.toquete.boxbox.core.common.di.commonModule
import com.toquete.boxbox.core.database.di.databaseModule
import com.toquete.boxbox.core.network.di.networkModule
import com.toquete.boxbox.core.preferences.di.preferencesModule
import com.toquete.boxbox.data.circuitimages.di.circuitImagesModule
import com.toquete.boxbox.data.constructorcolors.di.constructorColorsModule
import com.toquete.boxbox.data.constructorimages.di.constructorImagesModule
import com.toquete.boxbox.data.constructorstandings.di.constructorStandingsModule
import com.toquete.boxbox.data.countries.di.countriesModule
import com.toquete.boxbox.data.driverimages.di.driverImagesModule
import com.toquete.boxbox.data.driverstandings.di.driverStandingsModule
import com.toquete.boxbox.data.raceresults.di.raceResultsModule
import com.toquete.boxbox.data.races.di.racesModule
import com.toquete.boxbox.data.sprintresults.di.sprintResultsModule
import com.toquete.boxbox.di.appCheckModule
import com.toquete.boxbox.di.appModule
import com.toquete.boxbox.di.treeModule
import com.toquete.boxbox.domain.di.domainModule
import com.toquete.boxbox.feature.raceresults.di.raceResultsFeatureModule
import com.toquete.boxbox.feature.races.di.racesFeatureModule
import com.toquete.boxbox.feature.settings.di.settingsModule
import com.toquete.boxbox.feature.standings.di.standingsModule
import com.toquete.boxbox.sync.di.syncModule
import com.toquete.boxbox.sync.worker.SYNC_WORK_NAME
import com.toquete.boxbox.sync.worker.SyncWorker
import com.toquete.boxbox.util.remoteconfig.remoteConfigDefaults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okio.Path.Companion.toOkioPath
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

private const val APP_CHECK_DEBUG_STORE = "com.google.firebase.appcheck.debug.store.%s"
private const val APP_CHECK_DEBUG_TOKEN_KEY = "com.google.firebase.appcheck.debug.DEBUG_SECRET"
private const val MEMORY_CACHE_PERCENT = 0.1
private const val DISK_CACHE_PERCENT = 0.03
private const val MINIMUM_REMOTE_CONFIG_FETCH_INTERVAL = 0L

class BoxBoxApplication : Application(), SingletonImageLoader.Factory {

    private val timberTree: Timber.Tree by inject()
    private val appCheckProviderFactory: AppCheckProviderFactory by inject()
    private val ioDispatcher: CoroutineContext by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BoxBoxApplication)
            workManagerFactory()
            modules(
                commonModule,
                networkModule,
                databaseModule,
                preferencesModule,
                domainModule,
                driverStandingsModule,
                constructorStandingsModule,
                driverImagesModule,
                constructorImagesModule,
                constructorColorsModule,
                circuitImagesModule,
                racesModule,
                raceResultsModule,
                sprintResultsModule,
                countriesModule,
                syncModule,
                standingsModule,
                racesFeatureModule,
                raceResultsFeatureModule,
                settingsModule,
                appCheckModule,
                treeModule,
                appModule
            )
        }
        setupAppCheck()
        setupSyncWork()
        setupTimber()
        setupMobileAds()
        setupRemoteConfig()
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context, MEMORY_CACHE_PERCENT)
                    .strongReferencesEnabled(enable = true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(DISK_CACHE_PERCENT)
                    .directory(cacheDir.toOkioPath())
                    .build()
            }
            .build()
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
        CoroutineScope(ioDispatcher).launch {
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
