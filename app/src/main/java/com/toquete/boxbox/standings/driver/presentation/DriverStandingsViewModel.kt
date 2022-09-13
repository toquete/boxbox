package com.toquete.boxbox.standings.driver.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import com.toquete.boxbox.standings.driver.domain.usecase.GetDriverStandingsUseCase
import com.toquete.boxbox.standings.driver.presentation.model.DriversStandingModel
import com.toquete.boxbox.standings.driver.presentation.model.Nationality
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
            state = state.copy(isLoading = true)
            runCatching {
                getDriverStandingsUseCase()
            }.onSuccess { standings ->
                state = state.copy(
                    standings = standings.map { it.toModel() },
                    isLoading = false
                )
            }
        }
    }

    private fun DriverStanding.toModel(): DriversStandingModel {
        return DriversStandingModel(
            standing = this,
            nationality = Nationality.findByDemonym(nationality)
        )
    }
}