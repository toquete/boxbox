package com.toquete.boxbox

import android.app.Application
import androidx.core.content.edit
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
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
import com.toquete.boxbox.worker.SyncWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val APP_CHECK_DEBUG_STORE = "com.google.firebase.appcheck.debug.store.%s"
private const val APP_CHECK_DEBUG_TOKEN_KEY = "com.google.firebase.appcheck.debug.DEBUG_SECRET"
private const val MEMORY_CACHE_PERCENT = 0.1
private const val DISK_CACHE_PERCENT = 0.03
const val SYNC_WORK_NAME = "SYNC_WORK_NAME"

@HiltAndroidApp
class BoxBoxApplication : Application(), Configuration.Provider, ImageLoaderFactory {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var timberTree: Timber.Tree

    @Inject
    lateinit var appCheckProviderFactory: AppCheckProviderFactory

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
}
