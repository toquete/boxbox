package com.toquete.boxbox.feature.standings.drivers

import com.toquete.boxbox.core.model.DriverStanding

internal data class DriverStandingsState(
    val standings: List<DriverStanding> = emptyList()
)
