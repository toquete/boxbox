package com.toquete.boxbox.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class BoxBoxHttpClientTest {

    private fun buildClient(responseBody: String): BoxBoxHttpClient {
        val mockEngine = MockEngine {
            respond(
                content = responseBody,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }
        val httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true; explicitNulls = false })
            }
        }
        return BoxBoxHttpClient(httpClient)
    }

    @Test
    fun `getDriverStandings returns parsed data on success`() = runTest {
        val client = buildClient(DRIVER_STANDINGS_JSON)
        val result = client.getDriverStandings()
        assertEquals("2023", result.data.standingTable.season)
        assertEquals(1, result.data.standingTable.standingsLists.size)
    }

    @Test
    fun `getConstructorStandings returns parsed data on success`() = runTest {
        val client = buildClient(CONSTRUCTOR_STANDINGS_JSON)
        val result = client.getConstructorStandings()
        assertEquals("2023", result.data.standingTable.season)
        assertEquals(1, result.data.standingTable.standingsLists.size)
    }

    @Test
    fun `getRaces returns parsed data on success`() = runTest {
        val client = buildClient(RACES_JSON)
        val result = client.getRaces()
        assertEquals("2023", result.data.raceTable.season)
    }

    @Test
    fun `getRaceResults returns parsed data on success`() = runTest {
        val client = buildClient(RACE_RESULTS_JSON)
        val result = client.getRaceResults(offset = 0)
        assertEquals("2023", result.data.raceTable.season)
    }

    @Test
    fun `getSprintRaceResults returns parsed data on success`() = runTest {
        val client = buildClient(SPRINT_RESULTS_JSON)
        val result = client.getSprintRaceResults(offset = 0)
        assertEquals("2023", result.data.raceTable.season)
    }

    companion object {
        private val DRIVER_STANDINGS_JSON = readResourceText("driver_standings.json")
        private val CONSTRUCTOR_STANDINGS_JSON = readResourceText("constructor_standings.json")
        private val RACES_JSON = readResourceText("races.json")
        private val RACE_RESULTS_JSON = readResourceText("race_results.json")
        private val SPRINT_RESULTS_JSON = readResourceText("sprint_results.json")
    }
}

internal fun readResourceText(fileName: String): String =
    object {}.javaClass.classLoader!!.getResourceAsStream(fileName)!!
        .bufferedReader()
        .readText()
