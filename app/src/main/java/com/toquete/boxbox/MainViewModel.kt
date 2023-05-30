package com.toquete.boxbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.core.preferences.repository.UserPreferencesRepository
import com.toquete.boxbox.util.NetworkMonitor
import com.toquete.boxbox.util.SyncMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    networkMonitor: NetworkMonitor,
    syncMonitor: SyncMonitor,
    preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val state: StateFlow<MainState> = combine(
        networkMonitor.isOnline,
        syncMonitor.isSyncing,
        syncMonitor.hasFailed,
        preferencesRepository.userPreferences
    ) { isOnline, isSyncing, hasFailed, userPreferences ->
        MainState.Success(isOnline, isSyncing, hasFailed, userPreferences.darkThemeConfig)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainState.Loading
        )
}
