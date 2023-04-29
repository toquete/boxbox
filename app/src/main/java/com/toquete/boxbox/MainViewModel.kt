package com.toquete.boxbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.fullconstructorstandings.GetFullConstructorStandingsUseCase
import com.toquete.boxbox.domain.fulldriverstandings.GetFullDriverStandingsUseCase
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
    getFullDriverStandingsUseCase: GetFullDriverStandingsUseCase,
    getFullConstructorStandingsUseCase: GetFullConstructorStandingsUseCase,
    networkMonitor: NetworkMonitor,
    syncMonitor: SyncMonitor
) : ViewModel() {

    val isOnline: StateFlow<Boolean> = networkMonitor.isOnline
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true
        )

    val isSyncing: StateFlow<Boolean> = syncMonitor.isSyncing
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true
        )

    val state: StateFlow<MainState> = combine(
        getFullDriverStandingsUseCase(),
        getFullConstructorStandingsUseCase()
    ) { drivers, constructors ->
        MainState.Success(drivers, constructors)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainState.Loading
        )
}