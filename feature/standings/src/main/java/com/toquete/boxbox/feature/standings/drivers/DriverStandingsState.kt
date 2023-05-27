package com.toquete.boxbox.feature.standings.drivers

import com.toquete.boxbox.core.model.DriverStanding

internal sealed interface DriverStandingsState {
    object Loading : DriverStandingsState
    data class Success(val driverStandings: List<DriverStanding>) : DriverStandingsState
}
