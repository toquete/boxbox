package com.toquete.boxbox.features.standings.driver

import com.toquete.boxbox.domain.model.DriverStanding

data class DriverStandingsState(
    val isLoading: Boolean = false,
    val standings: List<DriverStanding> = emptyList()
)
