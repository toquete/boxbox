package com.toquete.boxbox.sync.monitor

import android.content.Context
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
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

@RunWith(AndroidJUnit4::class)
class WorkManagerSyncMonitorTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @After
    fun tearDown() {
        WorkManagerTestInitHelper.closeWorkDatabase()
    }

    @Test
    fun `isSyncing emits false when no work is enqueued`() = runTest {
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.isSyncing.first())
    }

    @Test
    fun `hasFailed emits false when no work is enqueued`() = runTest {
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.hasFailed.first())
    }

    @Test
    fun `isSyncing emits false when work is enqueued but not yet running`() = runTest {
        enqueueWork()
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.isSyncing.first())
    }

    @Test
    fun `hasFailed emits false when work is enqueued but not yet running`() = runTest {
        enqueueWork()
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.hasFailed.first())
    }

    @Test
    fun `isSyncing and hasFailed both emit false when work is cancelled`() = runTest {
        enqueueWork()
        WorkManager.getInstance(context).cancelUniqueWork(SYNC_WORK_NAME).result.get()
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.isSyncing.first())
        assertFalse(monitor.hasFailed.first())
    }

    @Test
    fun `two monitors on same context observe the same work state`() = runTest {
        enqueueWork()
        val monitor1 = WorkManagerSyncMonitor(context)
        val monitor2 = WorkManagerSyncMonitor(context)
        assertFalse(monitor1.isSyncing.first())
        assertFalse(monitor2.isSyncing.first())
    }

    private fun enqueueWork() {
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                SYNC_WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                SyncWorker.setupWork()
            )
            .result
            .get()
    }
}