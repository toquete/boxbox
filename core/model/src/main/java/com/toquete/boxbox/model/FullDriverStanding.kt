package com.toquete.boxbox.model

data class FullDriverStanding(
    val position: Int,
    val points: String,
    val wins: String,
    val driver: Driver,
    val constructor: Constructor
)
