package com.toquete.boxbox.standings.driver.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.standings.driver.domain.usecase.GetDriverStandingsUseCase
import kotlinx.coroutines.launch

class DriverStandingsViewModel(
    private val getDriverStandingsUseCase: GetDriverStandingsUseCase = GetDriverStandingsUseCase()
) : ViewModel() {

    var state by mutableStateOf(DriverStandingsState())
        private set

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        viewModelScope.launch {
            runCatching {
                val standings = getDriverStandingsUseCase()
                state = state.copy(standings = standings)
            }
        }
    }
}