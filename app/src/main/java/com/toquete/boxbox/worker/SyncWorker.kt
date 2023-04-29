package com.toquete.boxbox.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.toquete.boxbox.core.common.dispatcher.IoDispatcher
import com.toquete.boxbox.domain.fullconstructorstandings.SyncFullConstructorStandingsUseCase
import com.toquete.boxbox.domain.fulldriverstandings.SyncFullDriverStandingsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

private val syncConstraints = Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .build()

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    @IoDispatcher private val dispatcher: CoroutineContext,
    private val syncFullDriverStandingsUseCase: SyncFullDriverStandingsUseCase,
    private val syncFullConstructorStandingsUseCase: SyncFullConstructorStandingsUseCase
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result = withContext(dispatcher) {
        val isSuccess = awaitAll(
            async { syncFullDriverStandingsUseCase() },
            async { syncFullConstructorStandingsUseCase() }
        ).all { it }

        if (isSuccess) {
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