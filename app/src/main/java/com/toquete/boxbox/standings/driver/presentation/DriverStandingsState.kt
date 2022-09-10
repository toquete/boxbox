package com.toquete.boxbox.standings.driver.presentation

import com.toquete.boxbox.standings.driver.presentation.model.DriversStandingModel

data class DriverStandingsState(
    val isLoading: Boolean = false,
    val standings: List<DriversStandingModel> = emptyList()
)
