package com.toquete.boxbox.data.sprintresults.source.remote

import com.toquete.boxbox.core.common.extension.readPath
import com.toquete.boxbox.core.network.model.RacesWrapper
import kotlinx.serialization.json.Json

private const val SPRINT_RESULTS_JSON = "sprint_results.json"

internal class DefaultSprintResultRemoteDataSource(
    private val json: Json
) : SprintResultRemoteDataSource {

    override suspend fun getSprintResults(offset: Int): RacesWrapper {
        val response = readPath(SPRINT_RESULTS_JSON)
        return json.decodeFromString(response)
    }
}
