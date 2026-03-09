package com.toquete.boxbox.sync.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.domain.usecase.IsSyncAllowedUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.coroutines.cancellation.CancellationException

private val syncConstraints = Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .build()

const val SYNC_WORK_NAME = "SYNC_WORK_NAME"

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val syncRepository: SyncRepository,
    private val isSyncAllowedUseCase: IsSyncAllowedUseCase
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        return if (isSyncAllowedUseCase()) {
            sync()
        } else {
            Result.success()
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private suspend fun sync(): Result {
        return try {
            syncRepository.sync()
            Result.success()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Timber.e(e)
            Result.retry()
        }
    }

    companion object {
        fun setupWork() =
            PeriodicWorkRequestBuilder<SyncWorker>(repeatInterval = 1, repeatIntervalTimeUnit = TimeUnit.DAYS)
                .setConstraints(syncConstraints)
                .build()
    }
}
