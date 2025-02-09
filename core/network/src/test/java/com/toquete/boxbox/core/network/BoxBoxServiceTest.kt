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
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals

class BoxBoxServiceTest {

    private val mockWebServer = MockWebServer()
    private val service = NetworkModule.getRetrofitBuilder(
        json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        },
        baseUrl = mockWebServer.url("")
    )
        .build()
        .create(BoxBoxService::class.java)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getDriverStandings should return parsed data class on success`() = runTest {
        val expected = driverStandingsWrapper
        MockResponse().apply {
            setBody(readPath("driver_standings.json"))
            mockWebServer.enqueue(this)
        }

        val result = service.getDriverStandings()

        assertEquals(expected, result)
    }

    @Test
    fun `getDriverStandings should send correct request path when called`() = runTest {
        val expected = "/current/driverStandings.json"
        MockResponse().apply {
            setBody(readPath("driver_standings.json"))
            mockWebServer.enqueue(this)
        }

        service.getDriverStandings()

        assertEquals(expected, mockWebServer.takeRequest().path)
    }

    @Test
    fun `getConstructorStandings should return parsed data class on success`() = runTest {
        val expected = constructorStandingsWrapper
        MockResponse().apply {
            setBody(readPath("constructor_standings.json"))
            mockWebServer.enqueue(this)
        }

        val result = service.getConstructorStandings()

        assertEquals(expected, result)
    }

    @Test
    fun `getConstructorStandings should send correct request path when called`() = runTest {
        val expected = "/current/constructorStandings.json"
        MockResponse().apply {
            setBody(readPath("constructor_standings.json"))
            mockWebServer.enqueue(this)
        }

        service.getConstructorStandings()

        assertEquals(expected, mockWebServer.takeRequest().path)
    }

    @Test
    fun `getRaces should return parsed data class on success`() = runTest {
        val expected = racesWrapper
        MockResponse().apply {
            setBody(readPath("races.json"))
            mockWebServer.enqueue(this)
        }

        val result = service.getRaces()

        assertEquals(expected, result)
    }

    @Test
    fun `getRaces should send correct request path when called`() = runTest {
        val expected = "/current.json"
        MockResponse().apply {
            setBody(readPath("races.json"))
            mockWebServer.enqueue(this)
        }

        service.getRaces()

        assertEquals(expected, mockWebServer.takeRequest().path)
    }

    @Test
    fun `getRaceResults should return parsed data class on success`() = runTest {
        val expected = raceResultWrapper
        MockResponse().apply {
            setBody(readPath("race_results.json"))
            mockWebServer.enqueue(this)
        }

        val result = service.getRaceResults(offset = 0)

        assertEquals(expected, result)
    }

    @Test
    fun `getRaceResults should send correct request path when called`() = runTest {
        val expected = "/current/results.json?offset=0&limit=100"
        MockResponse().apply {
            setBody(readPath("race_results.json"))
            mockWebServer.enqueue(this)
        }

        service.getRaceResults(offset = 0)

        assertEquals(expected, mockWebServer.takeRequest().path)
    }

    @Test
    fun `getSprintRaceResults should return parsed data class on success`() = runTest {
        val expected = sprintRaceResultWrapper
        MockResponse().apply {
            setBody(readPath("sprint_results.json"))
            mockWebServer.enqueue(this)
        }

        val result = service.getSprintRaceResults(offset = 0)

        assertEquals(expected, result)
    }

    @Test
    fun `getSprintRaceResults should send correct request path when called`() = runTest {
        val expected = "/current/sprint.json?offset=0&limit=100"
        MockResponse().apply {
            setBody(readPath("sprint_results.json"))
            mockWebServer.enqueue(this)
        }

        service.getSprintRaceResults(offset = 0)

        assertEquals(expected, mockWebServer.takeRequest().path)
    }
}
