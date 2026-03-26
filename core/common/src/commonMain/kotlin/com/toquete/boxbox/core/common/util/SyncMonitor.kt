package com.toquete.boxbox.core.common.util

import kotlinx.coroutines.flow.Flow

interface SyncMonitor {
    val isSyncing: Flow<Boolean>
    val hasFailed: Flow<Boolean>
}
