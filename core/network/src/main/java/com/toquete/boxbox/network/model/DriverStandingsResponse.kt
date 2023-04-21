package com.toquete.boxbox.network.model

import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.Driver
import com.toquete.boxbox.model.DriverStanding
import com.toquete.boxbox.model.FullDriverStanding
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
    @SerialName("Driver")
    val driver: DriverResponse,
    @SerialName("Constructors")
    val constructors: List<ConstructorResponse>,
)

fun DriverStandingResponse.toDriverStanding(): DriverStanding {
    return DriverStanding(
        position = position.toInt(),
        points = points,
        driver = Driver(
            id = driver.id,
            firstName = driver.givenName,
            lastName = driver.familyName,
            imageUrl = null,
            flagUrl = null
        ),
        constructor = Constructor(
            id = constructors.first().id,
            name = constructors.first().name,
            imageUrl = null
        )
    )
}

fun DriverStandingResponse.toFullDriverStanding(): FullDriverStanding {
    return FullDriverStanding(
        position = position.toInt(),
        points = points,
        driver = Driver(
            id = driver.id,
            firstName = driver.givenName,
            lastName = driver.familyName,
            imageUrl = null,
            flagUrl = null
        ),
        constructor = Constructor(
            id = constructors.first().id,
            name = constructors.first().name,
            imageUrl = null
        )
    )
}
