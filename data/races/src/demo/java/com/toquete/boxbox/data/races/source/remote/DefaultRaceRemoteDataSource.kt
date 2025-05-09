package com.toquete.boxbox.data.races.source.remote

import com.toquete.boxbox.core.common.extension.readPath
import com.toquete.boxbox.core.network.model.RaceResponse
import kotlinx.serialization.json.Json

private const val RACES_JSON = "races.json"

internal class DefaultRaceRemoteDataSource(
    private val json: Json
) : RaceRemoteDataSource {

    override suspend fun getRaces(): List<RaceResponse> {
        val response = readPath(RACES_JSON)
        return json.decodeFromString(response)
    }
}
