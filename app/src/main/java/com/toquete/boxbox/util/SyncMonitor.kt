package com.toquete.boxbox.util

import kotlinx.coroutines.flow.Flow

interface SyncMonitor {
    val isSyncing: Flow<Boolean>
}
