package com.toquete.boxbox.sync.monitor

import android.content.Context
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.toquete.boxbox.sync.worker.SYNC_WORK_NAME
import com.toquete.boxbox.sync.worker.SyncWorker
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class WorkManagerSyncMonitorTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @After
    fun tearDown() {
        WorkManagerTestInitHelper.closeWorkDatabase()
    }

    @Test
    fun `isSyncing emits false when no work is enqueued`() = runTest {
        initWorkManager()
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.isSyncing.first())
    }

    @Test
    fun `hasFailed emits false when no work is enqueued`() = runTest {
        initWorkManager()
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.hasFailed.first())
    }

    @Test
    fun `isSyncing emits false when work is enqueued but not yet running`() = runTest {
        initWorkManager()
        enqueuePeriodicWork()
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.isSyncing.first())
    }

    @Test
    fun `hasFailed emits false when work is enqueued but not yet running`() = runTest {
        initWorkManager()
        enqueuePeriodicWork()
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.hasFailed.first())
    }

    @Test
    fun `isSyncing and hasFailed both emit false when work is cancelled`() = runTest {
        initWorkManager()
        enqueuePeriodicWork()
        WorkManager.getInstance(context).cancelUniqueWork(SYNC_WORK_NAME).result.get()
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.isSyncing.first())
        assertFalse(monitor.hasFailed.first())
    }

    @Test
    fun `two monitors on same context observe the same work state`() = runTest {
        initWorkManager()
        enqueuePeriodicWork()
        val monitor1 = WorkManagerSyncMonitor(context)
        val monitor2 = WorkManagerSyncMonitor(context)
        assertFalse(monitor1.isSyncing.first())
        assertFalse(monitor2.isSyncing.first())
    }

    @Test
    fun `hasFailed emits true when work has failed`() = runTest {
        initWorkManager(workerFactory = FailingWorkerFactory())
        val request = OneTimeWorkRequestBuilder<FailingWorker>().build()
        WorkManager.getInstance(context)
            .enqueueUniqueWork(SYNC_WORK_NAME, ExistingWorkPolicy.REPLACE, request)
            .result
            .get()
        WorkManagerTestInitHelper.getTestDriver(context)?.setAllConstraintsMet(request.id)
        val monitor = WorkManagerSyncMonitor(context)
        assertTrue(monitor.hasFailed.first())
    }

    private fun initWorkManager(workerFactory: WorkerFactory? = null) {
        val builder = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
        if (workerFactory != null) builder.setWorkerFactory(workerFactory)
        WorkManagerTestInitHelper.initializeTestWorkManager(context, builder.build())
    }

    private fun enqueuePeriodicWork() {
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                SYNC_WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                SyncWorker.setupWork()
            )
            .result
            .get()
    }

    private class FailingWorker(
        appContext: Context,
        workerParameters: WorkerParameters
    ) : CoroutineWorker(appContext, workerParameters) {
        override suspend fun doWork(): Result = Result.failure()
    }

    private class FailingWorkerFactory : WorkerFactory() {
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker = FailingWorker(appContext, workerParameters)
    }
}