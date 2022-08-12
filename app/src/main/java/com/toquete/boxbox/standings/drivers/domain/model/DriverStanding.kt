package com.toquete.boxbox.standings.drivers.domain.model

data class DriverStanding(
    val position: Int,
    val driver: String,
    val nationality: String,
    val car: String,
    val points: Int
)
