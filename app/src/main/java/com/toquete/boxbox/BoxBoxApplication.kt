package com.toquete.boxbox

import android.app.Application
import androidx.core.content.edit
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.appcheck.AppCheckProviderFactory
import com.google.firebase.appcheck.appCheck
import com.google.firebase.initialize
import com.toquete.boxbox.worker.SyncWorker
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

private const val APP_CHECK_DEBUG_STORE = "com.google.firebase.appcheck.debug.store.%s"
private const val APP_CHECK_DEBUG_TOKEN_KEY = "com.google.firebase.appcheck.debug.DEBUG_SECRET"
const val SYNC_WORK_NAME = "SYNC_WORK_NAME"

@HiltAndroidApp
class BoxBoxApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var timberTree: Timber.Tree

    @Inject
    lateinit var appCheckProviderFactory: AppCheckProviderFactory

    override fun onCreate() {
        super.onCreate()
        setupAppCheck()
        setupSyncWork()
        setupTimber()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    private fun setupSyncWork() {
        WorkManager.getInstance(this)
            .enqueueUniqueWork(
                SYNC_WORK_NAME,
                ExistingWorkPolicy.KEEP,
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
}
