package com.toquete.boxbox.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.domain.usecase.GetTodayLocalDateUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext
import kotlinx.datetime.DayOfWeek
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

private val syncConstraints = Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .build()

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val syncRepository: SyncRepository,
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase,
    @IoDispatcher private val dispatcher: CoroutineContext
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result = withContext(dispatcher) {
        val dayOfWeek = getTodayLocalDateUseCase().dayOfWeek

        if (!isDayOfWeekAllowed(dayOfWeek)) {
            Result.success()
        } else {
            sync()
        }
    }

    private fun isDayOfWeekAllowed(dayOfWeek: DayOfWeek): Boolean {
        return dayOfWeek in setOf(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
    }

    private suspend fun sync(): Result {
        return runCatching {
            syncRepository.sync()
        }.onFailure { error ->
            Timber.e(error)
        }.fold(
            onSuccess = { Result.success() },
            onFailure = { Result.retry() }
        )
    }

    companion object {
        fun setupWork() =
            PeriodicWorkRequestBuilder<SyncWorker>(repeatInterval = 1, repeatIntervalTimeUnit = TimeUnit.DAYS)
                .setConstraints(syncConstraints)
                .build()
    }
}
