package com.toquete.boxox.data.driverstandings.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal typealias DriverStandingsWrapper = DataResponse<StandingsTableResponse<StandingsLists<DriverStandingsResponse>>>

@Serializable
internal data class DriverStandingsResponse(
    @SerialName("DriverStandings")
    val driverStandings: List<StandingResponse>
)

@Serializable
internal data class StandingResponse(
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
