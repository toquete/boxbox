package com.toquete.boxbox.network.model

import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.ConstructorStanding
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
    @SerialName("Constructor")
    val constructor: ConstructorResponse
)

fun ConstructorStandingResponse.toConstructorStanding(): ConstructorStanding {
    return ConstructorStanding(
        position = position,
        points = points,
        constructor = Constructor(
            id = constructor.id,
            name = constructor.name,
            imageUrl = null
        )
    )
}