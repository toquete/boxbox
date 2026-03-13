package com.toquete.boxbox

import android.app.Application
import androidx.core.content.edit
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
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
import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.sync.worker.SYNC_WORK_NAME
import com.toquete.boxbox.sync.worker.SyncWorker
import com.toquete.boxbox.util.remoteconfig.remoteConfigDefaults
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okio.Path.Companion.toOkioPath
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val APP_CHECK_DEBUG_STORE = "com.google.firebase.appcheck.debug.store.%s"
private const val APP_CHECK_DEBUG_TOKEN_KEY = "com.google.firebase.appcheck.debug.DEBUG_SECRET"
private const val MEMORY_CACHE_PERCENT = 0.1
private const val DISK_CACHE_PERCENT = 0.03
private const val MINIMUM_REMOTE_CONFIG_FETCH_INTERVAL = 0L

@HiltAndroidApp
class BoxBoxApplication : Application(), Configuration.Provider, SingletonImageLoader.Factory {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var timberTree: Timber.Tree

    @Inject
    lateinit var appCheckProviderFactory: AppCheckProviderFactory

    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineContext

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
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
