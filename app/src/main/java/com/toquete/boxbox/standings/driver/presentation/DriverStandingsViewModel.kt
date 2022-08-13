package com.toquete.boxbox.standings.driver.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.flags.domain.model.Flag
import com.toquete.boxbox.flags.domain.usecase.GetFlagByDemonymUseCase
import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import com.toquete.boxbox.standings.driver.domain.usecase.GetDriverStandingsUseCase
import kotlinx.coroutines.launch

class DriverStandingsViewModel(
    private val getDriverStandingsUseCase: GetDriverStandingsUseCase = GetDriverStandingsUseCase(),
    private val getFlagByDemonymUseCase: GetFlagByDemonymUseCase = GetFlagByDemonymUseCase()
) : ViewModel() {

    var state by mutableStateOf(DriverStandingsState())
        private set

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        viewModelScope.launch {
            getDriverStandingsUseCase()
                .collect { standings ->
                    state = state.copy(standings = standings)
                    findDriverFlag(standings)
                }
        }
    }

    private fun findDriverFlag(standings: List<DriverStanding>) {
        viewModelScope.launch {
            val flagList = mutableListOf<Flag>()
            standings.forEach { standing ->
                getFlagByDemonymUseCase(standing.nationality)
                    .collect { flag ->
                        flagList.add(flag)
                    }
            }
            state = state.copy(flags = flagList)
        }
    }
}