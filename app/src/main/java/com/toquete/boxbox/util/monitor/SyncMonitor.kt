package com.toquete.boxbox.util.monitor

import kotlinx.coroutines.flow.Flow

interface SyncMonitor {
    val isSyncing: Flow<Boolean>
    val hasFailed: Flow<Boolean>
}
