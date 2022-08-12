package com.toquete.boxbox.standings.drivers.presentation

import com.toquete.boxbox.standings.drivers.domain.model.DriverStanding

data class DriverStandingsState(
    val standings: List<DriverStanding> = emptyList()
)
