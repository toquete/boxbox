package com.toquete.boxbox.network.model

import com.toquete.boxbox.model.ConstructorStanding
import com.toquete.boxbox.model.DriverStanding
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsTableResponse<T>(
    @SerialName("StandingsTable")
    val standingTable: T
)

@Serializable
data class StandingsLists<T>(
    @SerialName("StandingsLists")
    val standingsLists: List<T>
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

fun StandingResponse.toDriverStanding(): DriverStanding {
    return DriverStanding(
        position = position,
        name = driver?.givenName.orEmpty(),
        lastName = driver?.familyName.orEmpty(),
        nationality = driver?.nationality.orEmpty(),
        car = constructors?.first()?.name.orEmpty(),
        points = points
    )
}

fun StandingResponse.toConstructorStanding(): ConstructorStanding {
    return ConstructorStanding(
        position = position,
        name = constructor?.name.orEmpty(),
        points = points
    )
}
