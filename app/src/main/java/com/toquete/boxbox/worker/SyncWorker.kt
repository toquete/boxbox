package com.toquete.boxbox.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import com.toquete.boxbox.domain.usecase.GetTodayLocalDateUseCase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.DayOfWeek
import timber.log.Timber
import java.util.concurrent.TimeUnit

private val syncConstraints = Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .build()

class SyncWorker(
    appContext: Context,
    workerParameters: WorkerParameters,
    private val syncRepository: SyncRepository,
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase,
    private val userPreferencesRepository: UserPreferencesRepository
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        val dayOfWeek = getTodayLocalDateUseCase().dayOfWeek
        val lastUpdatedDate = userPreferencesRepository.userPreferences.firstOrNull()?.lastUpdatedDateInMillis

        return if (!isDayOfWeekAllowed(dayOfWeek) && lastUpdatedDate != null) {
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
