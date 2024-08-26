package com.toquete.boxbox.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.common.util.Syncable
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

private val syncConstraints = Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .build()

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val syncRepository: Syncable,
    @IoDispatcher private val dispatcher: CoroutineContext
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result = withContext(dispatcher) {
        runCatching {
            syncRepository.sync()
        }.onFailure { error ->
            ensureActive()
            Timber.e(error)
        }.fold(
            onSuccess = { Result.success() },
            onFailure = { Result.retry() }
        )
    }

    companion object {

        fun setupWork() = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(syncConstraints)
            .build()
    }
}
