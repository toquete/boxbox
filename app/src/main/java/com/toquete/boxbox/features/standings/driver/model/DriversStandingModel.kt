package com.toquete.boxbox.features.standings.driver.model

import com.toquete.boxbox.domain.model.DriverStanding

data class DriversStandingModel(
    val standing: DriverStanding,
    val nationality: Nationality
)
