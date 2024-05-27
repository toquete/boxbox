package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceResultResponse(
    @SerialName("position")
    val racePosition: String,
    @SerialName("points")
    val points: String,
    @SerialName("Driver")
    val driver: DriverResponse,
    @SerialName("Constructor")
    val constructor: ConstructorResponse,
    @SerialName("grid")
    val gridPosition: String,
    @SerialName("laps")
    val laps: String,
    @SerialName("status")
    val status: String,
    @SerialName("Time")
    val time: TimeResponse?
)
