package com.toquete.boxbox.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias DriverStandingsWrapper = DataResponse<StandingsTableResponse<StandingsLists<DriverStandingsResponse>>>

@Serializable
data class DriverStandingsResponse(
    @SerialName("DriverStandings")
    val driverStandings: List<DriverStandingResponse>
)

@Serializable
data class DriverStandingResponse(
    @SerialName("position")
    val position: String,
    @SerialName("points")
    val points: String,
    @SerialName("wins")
    val wins: String,
    @SerialName("Driver")
    val driver: DriverResponse,
    @SerialName("Constructors")
    val constructors: List<ConstructorResponse>,
)
