package com.toquete.boxbox.standings.driver.presentation.model

import com.toquete.boxbox.standings.driver.domain.model.DriverStanding

data class DriversStandingModel(
    val standing: DriverStanding,
    val nationality: Nationality
)
