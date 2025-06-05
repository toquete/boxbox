package com.toquete.boxbox.core.model

data class DriverStanding(
    val position: Int,
    val points: String,
    val wins: String,
    val driver: Driver,
    val constructor: Constructor
)
