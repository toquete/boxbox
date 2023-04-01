package com.toquete.boxbox.feature.driverstandings

import com.toquete.boxbox.model.DriverStanding

internal sealed interface DriverStandingsState {
    object Loading : DriverStandingsState
    data class Success(val standings: List<DriverStanding>) : DriverStandingsState
}