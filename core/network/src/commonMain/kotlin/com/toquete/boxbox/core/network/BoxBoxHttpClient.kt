package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.common.MAX_RESPONSE_LIMIT
import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.RacesWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://api.jolpi.ca/ergast/f1"

class BoxBoxHttpClient(private val client: HttpClient) {

    suspend fun getDriverStandings(season: String = "current"): DriverStandingsWrapper =
        client.get("$BASE_URL/$season/driverStandings.json").body()

    suspend fun getConstructorStandings(season: String = "current"): ConstructorStandingsWrapper =
        client.get("$BASE_URL/$season/constructorStandings.json").body()

    suspend fun getRaces(season: String = "current"): RacesWrapper =
        client.get("$BASE_URL/$season/races.json").body()

    suspend fun getRaceResults(
        season: String = "current",
        offset: Int,
        limit: Int = MAX_RESPONSE_LIMIT
    ): RacesWrapper =
        client.get("$BASE_URL/$season/results.json") {
            parameter("offset", offset)
            parameter("limit", limit)
        }.body()

    suspend fun getSprintRaceResults(
        season: String = "current",
        offset: Int,
        limit: Int = MAX_RESPONSE_LIMIT
    ): RacesWrapper =
        client.get("$BASE_URL/$season/sprint.json") {
            parameter("offset", offset)
            parameter("limit", limit)
        }.body()
}
