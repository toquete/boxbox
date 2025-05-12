package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.RacesWrapper
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class KtorService(private val httpClient: HttpClient) : BoxBoxService {

    override suspend fun getDriverStandings(): DriverStandingsWrapper {
        return httpClient.get("current/driverStandings.json").body()
    }

    override suspend fun getConstructorStandings(): ConstructorStandingsWrapper {
        return httpClient.get("current/constructorStandings.json").body()
    }

    override suspend fun getRaces(): RacesWrapper {
        return httpClient.get("current.json").body()
    }

    override suspend fun getRaceResults(offset: Int, limit: Int): RacesWrapper {
        return httpClient.get("current/results.json") {
            url {
                parameters.append("offset", offset.toString())
                parameters.append("limit", limit.toString())
            }
        }.body()
    }

    override suspend fun getSprintRaceResults(offset: Int, limit: Int): RacesWrapper {
        return httpClient.get("current/sprint.json") {
            url {
                parameters.append("offset", offset.toString())
                parameters.append("limit", limit.toString())
            }
        }.body()
    }
}
