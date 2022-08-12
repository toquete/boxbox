package com.toquete.boxbox.standings.drivers.presentation

import com.toquete.boxbox.standings.drivers.domain.model.DriverStanding

data class DriversStandingState(
    val standings: List<DriverStanding> = emptyList()
)
