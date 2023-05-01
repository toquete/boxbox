package com.toquete.boxbox.feature.standings.drivers

import com.toquete.boxbox.core.model.FullDriverStanding

internal sealed interface FullDriversStandingsState {
    object Loading : FullDriversStandingsState
    data class Success(val driverStandings: List<FullDriverStanding>) : FullDriversStandingsState
}