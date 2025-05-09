package com.toquete.boxbox.data.driverstandings.source.remote

import com.toquete.boxbox.core.common.extension.readPath
import com.toquete.boxbox.core.network.model.DriverStandingResponse
import kotlinx.serialization.json.Json

private const val DRIVER_STANDINGS_JSON = "driver_standings.json"

internal class DefaultDriverStandingsRemoteDataSource(
    private val json: Json
) : DriverStandingsRemoteDataSource {

    override suspend fun getDriverStandings(): List<DriverStandingResponse> {
        val response = readPath(DRIVER_STANDINGS_JSON)
        return json.decodeFromString(response)
    }
}
