package com.toquete.boxbox.feature.standings.drivers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class DriverStandingsViewModel(
    repository: DriverStandingsRepository
) : ViewModel() {

    val state = repository.getDriverStandings()
        .map { DriverStandingsState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DriverStandingsState()
        )
}
