package com.toquete.boxbox.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias ConstructorStandingsWrapper = DataResponse<StandingsTableResponse<StandingsLists<ConstructorStandingsResponse>>>

@Serializable
data class ConstructorStandingsResponse(
    @SerialName("ConstructorStandings")
    val constructorStandings: List<StandingResponse>
)
