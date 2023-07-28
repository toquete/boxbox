package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias RacesWrapper = DataResponse<RaceTableResponse>

@Serializable
data class RaceTableResponse(
    @SerialName("RaceTable")
    val raceTable: RaceDataResponse
)
