package com.toquete.boxbox.standings.driver.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias DriverStandingsWrapper = DataResponse<StandingsTableResponse<StandingsLists<DriverStandingsResponse>>>

@Serializable
data class DriverStandingsResponse(
    @SerialName("DriverStandings")
    val driverStandings: List<StandingResponse>
)

@Serializable
data class StandingResponse(
    @SerialName("position")
    val position: String,
    @SerialName("points")
    val points: String,
    @SerialName("Driver")
    val driver: DriverResponse? = null,
    @SerialName("Constructors")
    val constructors: List<ConstructorResponse>? = null,
    @SerialName("Constructor")
    val constructor: ConstructorResponse? = null
)
