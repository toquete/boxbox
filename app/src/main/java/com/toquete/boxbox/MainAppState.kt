package com.toquete.boxbox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.toquete.boxbox.core.common.STATE_FLOW_STOP_TIMEOUT
import com.toquete.boxbox.util.NetworkMonitor
import com.toquete.boxbox.util.SyncMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberMainAppState(
    networkMonitor: NetworkMonitor,
    syncMonitor: SyncMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(networkMonitor, syncMonitor, coroutineScope) {
    MainAppState(networkMonitor, syncMonitor, coroutineScope)
}

@Stable
class MainAppState(
    networkMonitor: NetworkMonitor,
    syncMonitor: SyncMonitor,
    coroutineScope: CoroutineScope
) {

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(STATE_FLOW_STOP_TIMEOUT),
            initialValue = false
        )

    val isSyncing = syncMonitor.isSyncing
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(STATE_FLOW_STOP_TIMEOUT),
            initialValue = true
        )

    val hasFailed = syncMonitor.hasFailed
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(STATE_FLOW_STOP_TIMEOUT),
            initialValue = false
        )
}
