package com.toquete.boxbox.util

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.toquete.boxbox.di.SYNC_WORK_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkManagerSyncMonitor @Inject constructor(
    @ApplicationContext context: Context
) : SyncMonitor {

    override val isSyncing: Flow<Boolean> = WorkManager.getInstance(context)
        .getWorkInfosForUniqueWorkLiveData(SYNC_WORK_NAME)
        .map { workInfos ->
            workInfos.any { it.state == WorkInfo.State.RUNNING }
        }
        .asFlow()
}
