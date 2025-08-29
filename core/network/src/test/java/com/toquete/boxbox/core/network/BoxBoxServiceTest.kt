package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.common.extension.readPath
import com.toquete.boxbox.core.network.di.NetworkModule
import com.toquete.boxbox.core.testing.data.constructorStandingsWrapper
import com.toquete.boxbox.core.testing.data.driverStandingsWrapper
import com.toquete.boxbox.core.testing.data.raceResultWrapper
import com.toquete.boxbox.core.testing.data.racesWrapper
import com.toquete.boxbox.core.testing.data.sprintRaceResultWrapper
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals

class BoxBoxServiceTest {

    private val mockWebServer = MockWebServer()
    private lateinit var service: BoxBoxService

    @After
    fun tearDown() {
        mockWebServer.close()
    }

    @Test
    fun `getDriverStandings should return parsed data class on success`() = runTest {
        val expected = driverStandingsWrapper
        with(mockWebServer) {
            enqueue(MockResponse(body = readPath("driver_standings.json")))
            start()
            setupService()
        }

        val result = service.getDriverStandings()

        assertEquals(expected, result)
    }

    @Test
    fun `getDriverStandings should send correct request path when called`() = runTest {
        val expected = "/current/driverStandings.json"
        with(mockWebServer) {
            enqueue(MockResponse(body = readPath("driver_standings.json")))
            start()
            setupService()
        }

        service.getDriverStandings()

        assertEquals(expected, mockWebServer.takeRequest().url.encodedPath)
    }

    @Test
    fun `getConstructorStandings should return parsed data class on success`() = runTest {
        val expected = constructorStandingsWrapper
        with(mockWebServer) {
            enqueue(MockResponse(body = readPath("constructor_standings.json")))
            start()
            setupService()
        }

        val result = service.getConstructorStandings()

        assertEquals(expected, result)
    }

    @Test
    fun `getConstructorStandings should send correct request path when called`() = runTest {
        val expected = "/current/constructorStandings.json"
        with(mockWebServer) {
            enqueue(MockResponse(body = readPath("constructor_standings.json")))
            start()
            setupService()
        }

        service.getConstructorStandings()

        assertEquals(expected, mockWebServer.takeRequest().url.encodedPath)
    }

    @Test
    fun `getRaces should return parsed data class on success`() = runTest {
        val expected = racesWrapper
        with(mockWebServer) {
            enqueue(MockResponse(body = readPath("races.json")))
            start()
            setupService()
        }

        val result = service.getRaces()

        assertEquals(expected, result)
    }

    @Test
    fun `getRaces should send correct request path when called`() = runTest {
        val expected = "/current.json"
        with(mockWebServer) {
            enqueue(MockResponse(body = readPath("races.json")))
            start()
            setupService()
        }

        service.getRaces()

        assertEquals(expected, mockWebServer.takeRequest().url.encodedPath)
    }

    @Test
    fun `getRaceResults should return parsed data class on success`() = runTest {
        val expected = raceResultWrapper
        with(mockWebServer) {
            enqueue(MockResponse(body = readPath("race_results.json")))
            start()
            setupService()
        }

        val result = service.getRaceResults(offset = 0)

        assertEquals(expected, result)
    }

    @Test
    fun `getRaceResults should send correct request path when called`() = runTest {
        val expectedPath = "/current/results.json"
        val expectedParams = "offset=0&limit=100"
        with(mockWebServer) {
            enqueue(MockResponse(body = readPath("race_results.json")))
            start()
            setupService()
        }

        service.getRaceResults(offset = 0)

        val request = mockWebServer.takeRequest()
        assertEquals(expectedPath, request.url.encodedPath)
        assertEquals(expectedParams, request.url.encodedQuery)
    }

    @Test
    fun `getSprintRaceResults should return parsed data class on success`() = runTest {
        val expected = sprintRaceResultWrapper
        with(mockWebServer) {
            enqueue(MockResponse(body = readPath("sprint_results.json")))
            start()
            setupService()
        }

        val result = service.getSprintRaceResults(offset = 0)

        assertEquals(expected, result)
    }

    @Test
    fun `getSprintRaceResults should send correct request path when called`() = runTest {
        val expectedPath = "/current/sprint.json"
        val expectedParams = "offset=0&limit=100"
        with(mockWebServer) {
            enqueue(MockResponse(body = readPath("sprint_results.json")))
            start()
            setupService()
        }

        service.getSprintRaceResults(offset = 0)

        val request = mockWebServer.takeRequest()
        assertEquals(expectedPath, request.url.encodedPath)
        assertEquals(expectedParams, request.url.encodedQuery)
    }

    private fun setupService() {
        service = NetworkModule.getRetrofitBuilder(
            json = Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            },
            baseUrl = mockWebServer.url("")
        )
            .build()
            .create(BoxBoxService::class.java)
    }
}
