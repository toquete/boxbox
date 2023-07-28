package com.toquete.boxbox.data.constructorstandings.source.remote

import com.toquete.boxbox.core.common.extension.readPath
import com.toquete.boxbox.core.network.model.ConstructorStandingResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

private const val CONSTRUCTOR_STANDINGS_JSON = "constructor_standings.json"

internal class DefaultConstructorStandingsRemoteDataSource @Inject constructor(
    private val json: Json
) : ConstructorStandingsRemoteDataSource {

    override suspend fun getConstructorStandings(): List<ConstructorStandingResponse> {
        val response = readPath(CONSTRUCTOR_STANDINGS_JSON)
        return json.decodeFromString(response)
    }
}
