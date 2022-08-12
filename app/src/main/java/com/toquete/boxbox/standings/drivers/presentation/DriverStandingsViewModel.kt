package com.toquete.boxbox.standings.drivers.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.standings.drivers.domain.usecase.GetDriverStandingsUseCase
import kotlinx.coroutines.launch

class DriverStandingsViewModel(
    private val getDriverStandingsUseCase: GetDriverStandingsUseCase = GetDriverStandingsUseCase()
) : ViewModel() {

    var state by mutableStateOf(DriversStandingState())
        private set

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        viewModelScope.launch {
            getDriverStandingsUseCase()
                .collect { standings ->
                    state = state.copy(standings = standings)
                }
        }
    }
}