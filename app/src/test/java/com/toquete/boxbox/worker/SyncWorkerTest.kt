package com.toquete.boxbox.worker

import android.content.Context
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.toquete.boxbox.core.common.util.Syncable
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.runs
import io.mockk.unmockkObject
import io.mockk.verify
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber
import java.io.IOException
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class SyncWorkerTest {

    private val syncRepository: Syncable = mockk()
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .setWorkerFactory(TestWorkerFactory(syncRepository))
            .build()

        // Initialize WorkManager for instrumentation tests.
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    fun `doWork returns success when sync is successful`() {
        val worker = TestListenableWorkerBuilder<SyncWorker>(context)
            .setWorkerFactory(TestWorkerFactory(syncRepository))
            .build()

        coEvery { syncRepository.sync() } just runs

        runTest {
            val result = worker.doWork()
            assertEquals(ListenableWorker.Result.success(), result)
        }
    }

    @Test
    fun `doWork returns retry when sync is not successful`() {
        val exception = IOException()
        val worker = TestListenableWorkerBuilder<SyncWorker>(context)
            .setWorkerFactory(TestWorkerFactory(syncRepository))
            .build()

        coEvery { syncRepository.sync() } throws exception
        mockkObject(Timber)

        try {
            runTest {
                val result = worker.doWork()
                assertEquals(ListenableWorker.Result.retry(), result)
                verify { Timber.e(exception) }
            }
        } finally {
            unmockkObject(Timber)
        }
    }

    @Test
    fun `test worker with constraints`() {
        val request = SyncWorker.setupWork()
        val workManager = WorkManager.getInstance(context).apply {
            enqueue(request).result.get()
        }

        coEvery { syncRepository.sync() } just runs
        WorkManagerTestInitHelper.getTestDriver(context)
            ?.setAllConstraintsMet(request.id)

        val workInfo = workManager.getWorkInfoById(request.id).get()
        assertEquals(WorkInfo.State.SUCCEEDED, workInfo?.state)
    }

    private class TestWorkerFactory(
        private val syncRepository: Syncable,
        private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    ) : WorkerFactory() {
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return SyncWorker(appContext, workerParameters, syncRepository, dispatcher)
        }
    }
}
