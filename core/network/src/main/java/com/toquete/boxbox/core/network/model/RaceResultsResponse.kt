package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceResultsResponse(
    @SerialName("position")
    val racePosition: String,
    @SerialName("Driver")
    val driver: DriverResponse,
    @SerialName("Constructor")
    val constructor: ConstructorResponse,
    @SerialName("grid")
    val gridPosition: String
)
