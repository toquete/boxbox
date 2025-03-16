package com.toquete.boxbox.util.monitor

import android.content.Context
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.toquete.boxbox.SYNC_WORK_NAME
import com.toquete.boxbox.core.common.util.SyncMonitor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WorkManagerSyncMonitor @Inject constructor(
    @ApplicationContext context: Context
) : SyncMonitor {

    private val workInfos = WorkManager.getInstance(context)
        .getWorkInfosForUniqueWorkFlow(SYNC_WORK_NAME)

    override val isSyncing: Flow<Boolean> = workInfos.map { infos ->
        infos.any { it.state == WorkInfo.State.RUNNING }
    }
    override val hasFailed: Flow<Boolean> = workInfos.map { infos ->
        infos.any { it.state == WorkInfo.State.FAILED }
    }
}
