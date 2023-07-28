package com.toquete.boxbox.data.races.source.remote

import com.toquete.boxbox.core.common.extension.readPath
import com.toquete.boxbox.core.network.model.RaceResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

private const val RACES_JSON = "races.json"

internal class DefaultRaceRemoteDataSource @Inject constructor() : RaceRemoteDataSource {

    override suspend fun getRaces(): List<RaceResponse> {
        val response = RACES_JSON.readPath()
        return Json.decodeFromString(response)
    }
}
