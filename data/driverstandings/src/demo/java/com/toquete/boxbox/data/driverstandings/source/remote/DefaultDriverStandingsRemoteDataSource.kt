package com.toquete.boxbox.data.driverstandings.source.remote

import com.toquete.boxbox.core.common.extension.readPath
import com.toquete.boxbox.core.network.model.DriverStandingResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

private const val DRIVER_STANDINGS_JSON = "driver_standings.json"

internal class DefaultDriverStandingsRemoteDataSource @Inject constructor() : DriverStandingsRemoteDataSource {

    override suspend fun getDriverStandings(): List<DriverStandingResponse> {
        val response = DRIVER_STANDINGS_JSON.readPath()
        return Json.decodeFromString(response)
    }
}
