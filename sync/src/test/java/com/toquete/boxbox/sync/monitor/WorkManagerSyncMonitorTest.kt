package com.toquete.boxbox.sync.monitor

import android.content.Context
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
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
    fun `isSyncing emits false when no work is running`() = runTest {
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.isSyncing.first())
    }

    @Test
    fun `hasFailed emits false when no work has failed`() = runTest {
        val monitor = WorkManagerSyncMonitor(context)
        assertFalse(monitor.hasFailed.first())
    }
}