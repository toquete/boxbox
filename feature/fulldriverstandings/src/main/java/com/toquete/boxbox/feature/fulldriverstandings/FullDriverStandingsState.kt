package com.toquete.boxbox.feature.fulldriverstandings

import com.toquete.boxbox.model.FullDriverStanding

internal sealed interface FullDriverStandingsState {
    data class Success(val standings: List<FullDriverStanding>) : FullDriverStandingsState
}