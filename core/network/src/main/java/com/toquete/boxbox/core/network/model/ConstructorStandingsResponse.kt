package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias ConstructorStandingsWrapper = DataResponse<StandingsTableResponse<StandingsLists<ConstructorStandingsResponse>>>

@Serializable
data class ConstructorStandingsResponse(
    @SerialName("ConstructorStandings")
    val constructorStandings: List<ConstructorStandingResponse>
)

@Serializable
data class ConstructorStandingResponse(
    @SerialName("position")
    val position: String,
    @SerialName("points")
    val points: String,
    @SerialName("wins")
    val wins: String,
    @SerialName("Constructor")
    val constructor: ConstructorResponse
)
