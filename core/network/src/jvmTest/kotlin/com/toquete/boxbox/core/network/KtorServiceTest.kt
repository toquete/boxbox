package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.common.extension.readPath
import com.toquete.boxbox.core.network.ktor.KtorService
import com.toquete.boxbox.core.network.mock.constructorStandingsWrapper
import com.toquete.boxbox.core.network.mock.driverStandingsWrapper
import com.toquete.boxbox.core.network.mock.raceResultWrapper
import com.toquete.boxbox.core.network.mock.racesWrapper
import com.toquete.boxbox.core.network.mock.sprintRaceResultWrapper
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class KtorServiceTest {

    @Test
    fun `getDriverStandings should return parsed data class on success`() = runTest {
        val expected = driverStandingsWrapper
        val mockEngine = MockEngine { _ ->
            respond(
                content = readPath("driver_standings.json"),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val service = KtorService(mockEngine)

        val result = service.getDriverStandings()
        val segments = mockEngine.requestHistory.last().url.segments

        assertEquals("current", segments[segments.size - 2])
        assertEquals("driverStandings.json", segments[segments.size - 1])
        assertEquals(expected, result)
    }

    @Test
    fun `getConstructorStandings should return parsed data class on success`() = runTest {
        val expected = constructorStandingsWrapper
        val mockEngine = MockEngine { _ ->
            respond(
                content = readPath("constructor_standings.json"),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val service = KtorService(mockEngine)

        val result = service.getConstructorStandings()
        val segments = mockEngine.requestHistory.last().url.segments

        assertEquals("current", segments[segments.size - 2])
        assertEquals("constructorStandings.json", segments[segments.size - 1])
        assertEquals(expected, result)
    }

    @Test
    fun `getRaces should return parsed data class on success`() = runTest {
        val expected = racesWrapper
        val mockEngine = MockEngine { _ ->
            respond(
                content = readPath("races.json"),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val service = KtorService(mockEngine)

        val result = service.getRaces()
        val segments = mockEngine.requestHistory.last().url.segments

        assertEquals("current.json", segments.last())
        assertEquals(expected, result)
    }

    @Test
    fun `getRaceResults should return parsed data class on success`() = runTest {
        val expected = raceResultWrapper
        val mockEngine = MockEngine { _ ->
            respond(
                content = readPath("race_results.json"),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val service = KtorService(mockEngine)

        val result = service.getRaceResults(offset = 0)
        val segments = mockEngine.requestHistory.last().url.segments
        val query = mockEngine.requestHistory.last().url.encodedQuery

        assertEquals("current", segments[segments.size - 2])
        assertEquals("results.json", segments[segments.size - 1])
        assertEquals("offset=0&limit=100", query)
        assertEquals(expected, result)
    }

    @Test
    fun `getSprintRaceResults should return parsed data class on success`() = runTest {
        val expected = sprintRaceResultWrapper
        val mockEngine = MockEngine { _ ->
            respond(
                content = readPath("sprint_results.json"),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val service = KtorService(mockEngine)

        val result = service.getSprintRaceResults(offset = 0)
        val segments = mockEngine.requestHistory.last().url.segments
        val query = mockEngine.requestHistory.last().url.encodedQuery

        assertEquals("current", segments[segments.size - 2])
        assertEquals("sprint.json", segments[segments.size - 1])
        assertEquals("offset=0&limit=100", query)
        assertEquals(expected, result)
    }
}
