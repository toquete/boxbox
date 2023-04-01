package com.toquete.boxbox.features.standings.driver

import com.toquete.boxbox.model.DriverStanding

sealed interface DriverStandingsState {
    object Loading : DriverStandingsState
    data class Success(val standings: List<DriverStanding>) : DriverStandingsState
}