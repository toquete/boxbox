package com.toquete.boxbox.feature.standings.drivers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.data.driverstandings.repository.DriverStandingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class DriverStandingsViewModel @Inject constructor(
    repository: DriverStandingsRepository
) : ViewModel() {

    val state = repository.getDriverStandings()
        .map { DriverStandingsState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DriverStandingsState.Loading
        )
}
