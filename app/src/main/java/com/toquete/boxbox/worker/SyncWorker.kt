package com.toquete.boxbox.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.toquete.boxbox.worker.repository.SyncRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

private val syncConstraints = Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .build()

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val syncRepository: SyncRepository
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        return if (syncRepository.sync()) {
            Result.success()
        } else {
            Result.retry()
        }
    }

    companion object {

        fun setupWork() = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(syncConstraints)
            .build()
    }
}
