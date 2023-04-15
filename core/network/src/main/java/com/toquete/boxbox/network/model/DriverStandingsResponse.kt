package com.toquete.boxbox.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias DriverStandingsWrapper = DataResponse<StandingsTableResponse<StandingsLists<DriverStandingsResponse>>>

@Serializable
data class DriverStandingsResponse(
    @SerialName("DriverStandings")
    val driverStandings: List<StandingResponse>
)
