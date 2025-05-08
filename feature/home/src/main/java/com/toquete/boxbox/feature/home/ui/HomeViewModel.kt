package com.toquete.boxbox.feature.home.ui

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.core.common.STATE_FLOW_STOP_TIMEOUT
import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.core.common.util.SyncMonitor
import com.toquete.boxbox.core.ui.custom.SnackbarManager
import com.toquete.boxbox.domain.repository.RemoteConfigRepository
import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.feature.home.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

internal class HomeViewModel(
    syncMonitor: SyncMonitor,
    networkMonitor: NetworkMonitor,
    private val syncRepository: SyncRepository,
    remoteConfigRepository: RemoteConfigRepository
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val state: StateFlow<HomeState> = combine(
        networkMonitor.isOnline,
        syncMonitor.isSyncing,
        syncMonitor.hasFailed,
        _isRefreshing,
        remoteConfigRepository.remoteConfigs
    ) { isOnline, isSyncing, hasFailed, isRefreshing, remoteConfigs ->
        HomeState(
            isOffline = !isOnline,
            isSyncing = isSyncing,
            hasFailed = hasFailed,
            isRefreshing = isRefreshing,
            isAdBannerVisible = remoteConfigs.isAdBannerVisible
        )
    }.onEach { state ->
        val message = when {
            state.isOffline -> R.string.home_not_connected
            state.hasFailed -> R.string.home_fail_message
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

    fun refresh() {
        _isRefreshing.update { true }
        viewModelScope.launch {
            runCatching {
                syncRepository.sync()
            }.onFailure {
                Timber.e(it)
                SnackbarManager.showMessage(
                    messageTextId = R.string.home_fail_refresh,
                    duration = SnackbarDuration.Long,
                    withDismissAction = true
                )
            }.also {
                _isRefreshing.update { false }
            }
        }
    }
}
