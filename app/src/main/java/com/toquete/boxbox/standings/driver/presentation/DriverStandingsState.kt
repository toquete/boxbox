package com.toquete.boxbox.standings.driver.presentation

import com.toquete.boxbox.standings.driver.domain.model.DriverStanding

data class DriverStandingsState(
    val standings: List<DriverStanding> = emptyList()
)
