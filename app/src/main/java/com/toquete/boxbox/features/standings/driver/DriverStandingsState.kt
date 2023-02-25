package com.toquete.boxbox.features.standings.driver

import com.toquete.boxbox.features.standings.driver.model.DriversStandingModel

data class DriverStandingsState(
    val isLoading: Boolean = false,
    val standings: List<DriversStandingModel> = emptyList()
)
