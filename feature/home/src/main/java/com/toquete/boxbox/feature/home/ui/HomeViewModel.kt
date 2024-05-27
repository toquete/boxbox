package com.toquete.boxbox.feature.home.ui

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.core.common.STATE_FLOW_STOP_TIMEOUT
import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.core.common.util.SyncMonitor
import com.toquete.boxbox.core.ui.custom.SnackbarManager
import com.toquete.boxbox.feature.home.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    syncMonitor: SyncMonitor,
    networkMonitor: NetworkMonitor
) : ViewModel() {

    val state: StateFlow<HomeState> = combine(
        networkMonitor.isOnline,
        syncMonitor.isSyncing,
        syncMonitor.hasFailed
    ) { isOnline, isSyncing, hasFailed ->
        HomeState(
            isOffline = !isOnline,
            isSyncing = isSyncing,
            hasFailed = hasFailed
        )
    }.onEach { state ->
        val message = when {
            state.isOffline -> R.string.not_connected
            state.hasFailed -> R.string.fail_message
            else -> null
        }

        message?.let {
            SnackbarManager.showMessage(it, duration = SnackbarDuration.Long)
        }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STATE_FLOW_STOP_TIMEOUT),
        initialValue = HomeState()
    )
}
