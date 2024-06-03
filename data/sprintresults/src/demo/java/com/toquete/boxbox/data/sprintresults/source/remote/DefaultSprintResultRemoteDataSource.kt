package com.toquete.boxbox.data.sprintresults.source.remote

import com.toquete.boxbox.core.common.extension.readPath
import com.toquete.boxbox.core.network.model.RaceResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

private const val SPRINT_RESULTS_JSON = "sprint_results.json"

internal class DefaultSprintResultRemoteDataSource @Inject constructor(
    private val json: Json
) : SprintResultRemoteDataSource {

    override suspend fun getSprintResults(): List<RaceResponse> {
        val response = readPath(SPRINT_RESULTS_JSON)
        return json.decodeFromString(response)
    }
}
