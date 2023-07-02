package com.toquete.boxbox.util.monitor

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.toquete.boxbox.SYNC_WORK_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val FIRST_ATTEMPT_COUNT = 1

class WorkManagerSyncMonitor @Inject constructor(
    @ApplicationContext context: Context
) : SyncMonitor {

    private val workInfos = WorkManager.getInstance(context)
        .getWorkInfosForUniqueWorkLiveData(SYNC_WORK_NAME)
        .asFlow()

    override val isSyncing: Flow<Boolean> = workInfos.map { infos ->
        infos.any { it.state == WorkInfo.State.RUNNING }
    }
    override val hasFailed: Flow<Boolean> = workInfos.map { infos ->
        infos.any {
            it.state == WorkInfo.State.ENQUEUED && it.runAttemptCount > FIRST_ATTEMPT_COUNT
        }
    }
}
