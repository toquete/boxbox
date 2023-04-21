package com.toquete.boxbox.feature.driverstandings

import com.toquete.boxbox.model.FullDriverStanding

internal sealed interface FullDriverStandingsState {
    object Loading : FullDriverStandingsState
    data class Success(val standings: List<FullDriverStanding>) : FullDriverStandingsState
}