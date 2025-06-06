package com.toquete.boxbox.core.network.ktor

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.RacesWrapper
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorService(engine: HttpClientEngine) : BoxBoxService {

    private val httpClient = HttpClient(engine) {
        defaultRequest {
            url(BASE_URL)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            format = LoggingFormat.OkHttp
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }
    }

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

    companion object {
        private const val BASE_URL = "https://api.jolpi.ca/ergast/f1/"
    }
}
