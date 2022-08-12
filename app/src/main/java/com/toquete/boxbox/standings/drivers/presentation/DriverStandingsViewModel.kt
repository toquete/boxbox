package com.toquete.boxbox.standings.drivers.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.standings.drivers.domain.usecase.GetDriversStandingUseCase
import kotlinx.coroutines.launch

class DriverStandingsViewModel(
    private val getDriversStandingUseCase: GetDriversStandingUseCase = GetDriversStandingUseCase()
) : ViewModel() {

    var state by mutableStateOf(DriversStandingState())
        private set

    init {
        getDriversStanding()
    }

    private fun getDriversStanding() {
        viewModelScope.launch {
            getDriversStandingUseCase()
                .collect { standings ->
                    state = state.copy(standings = standings)
                }
        }
    }
}