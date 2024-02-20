package com.toquete.boxbox.core.model

data class RaceResult(
    val season: String,
    val round: Int,
    val position: Int,
    val driver: Driver,
    val constructor: Constructor,
    val gridPosition: Int
)
