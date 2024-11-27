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
import com.toquete.boxbox.domain.usecase.GetTodayLocalDateUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.runs
import io.mockk.unmockkObject
import io.mockk.verify
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber
import java.io.IOException
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class SyncWorkerTest {

    private val syncRepository: Syncable = mockk()
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase = mockk()
    private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    private val testWorkerFactory = TestWorkerFactory(syncRepository, getTodayLocalDateUseCase, dispatcher)
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .setWorkerFactory(testWorkerFactory)
            .build()

        // Initialize WorkManager for instrumentation tests.
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    fun `doWork returns success when sync is successful`() {
        val worker = TestListenableWorkerBuilder<SyncWorker>(context)
            .setWorkerFactory(testWorkerFactory)
            .build()

        coEvery { syncRepository.sync() } just runs
        every { getTodayLocalDateUseCase() } returns LocalDate(
            year = 2024,
            monthNumber = 12,
            dayOfMonth = 1 // Sunday
        )

        runTest {
            val result = worker.doWork()
            assertEquals(ListenableWorker.Result.success(), result)
        }
    }

    @Test
    fun `doWork returns success when day of week is not Sunday or Monday`() {
        val worker = TestListenableWorkerBuilder<SyncWorker>(context)
            .setWorkerFactory(testWorkerFactory)
            .build()

        coEvery { syncRepository.sync() } just runs
        every { getTodayLocalDateUseCase() } returns LocalDate(
            year = 2024,
            monthNumber = 12,
            dayOfMonth = 3 // Tuesday
        )

        runTest {
            val result = worker.doWork()
            assertEquals(ListenableWorker.Result.success(), result)
            coVerify(inverse = true) { syncRepository.sync() }
        }
    }

    @Test
    fun `doWork returns retry when sync is not successful`() {
        val exception = IOException()
        val worker = TestListenableWorkerBuilder<SyncWorker>(context)
            .setWorkerFactory(testWorkerFactory)
            .build()

        coEvery { syncRepository.sync() } throws exception
        every { getTodayLocalDateUseCase() } returns LocalDate(
            year = 2024,
            monthNumber = 12,
            dayOfMonth = 2 // Monday
        )
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
        val testDriver = WorkManagerTestInitHelper.getTestDriver(context)
        val workManager = WorkManager.getInstance(context).apply {
            enqueue(request).result.get()
        }

        coEvery { syncRepository.sync() } just runs
        every { getTodayLocalDateUseCase() } returns LocalDate(year = 2024, monthNumber = 12, dayOfMonth = 1)
        testDriver?.setAllConstraintsMet(request.id)
        testDriver?.setPeriodDelayMet(request.id)

        val workInfo = workManager.getWorkInfoById(request.id).get()
        assertEquals(WorkInfo.State.ENQUEUED, workInfo?.state)
    }

    private class TestWorkerFactory(
        private val syncRepository: Syncable,
        private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase,
        private val dispatcher: CoroutineDispatcher
    ) : WorkerFactory() {
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return SyncWorker(
                appContext = appContext,
                workerParameters = workerParameters,
                syncRepository = syncRepository,
                getTodayLocalDateUseCase = getTodayLocalDateUseCase,
                dispatcher = dispatcher
            )
        }
    }
}
