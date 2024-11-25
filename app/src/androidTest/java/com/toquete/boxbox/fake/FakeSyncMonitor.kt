package com.toquete.boxbox.fake

import com.toquete.boxbox.core.common.util.SyncMonitor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSyncMonitor : SyncMonitor {
    override val isSyncing: Flow<Boolean>
        get() = flowOf(false)
    override val hasFailed: Flow<Boolean>
        get() = flowOf(false)
}
