package com.toquete.boxbox.data.raceresults.source.remote

import com.toquete.boxbox.core.common.extension.readPath
import com.toquete.boxbox.core.network.model.RaceResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

private const val RACE_RESULTS_JSON = "race_results.json"

internal class DefaultRaceResultRemoteDataSource @Inject constructor(
    private val json: Json
) : RaceResultRemoteDataSource {

    override suspend fun getRaceResults(): List<RaceResponse> {
        val response = readPath(RACE_RESULTS_JSON)
        return json.decodeFromString(response)
    }
}
