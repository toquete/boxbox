package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.network.di.NetworkModule
import com.toquete.boxbox.core.network.extension.readPath
import com.toquete.boxbox.core.testing.data.constructorStandingsWrapper
import com.toquete.boxbox.core.testing.data.driverStandingsWrapper
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals

class BoxBoxServiceTest {

    private val mockWebServer = MockWebServer()
    private val service = NetworkModule.getRetrofitBuilder(baseUrl = mockWebServer.url(""))
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
            setBody("driver_standings.json".readPath())
            mockWebServer.enqueue(this)
        }

        val result = service.getDriverStandings()

        assertEquals(expected, result)
    }

    @Test
    fun `getDriverStandings should send correct request path when called`() = runTest {
        val expected = "/current/driverStandings.json"
        MockResponse().apply {
            setBody("driver_standings.json".readPath())
            mockWebServer.enqueue(this)
        }

        service.getDriverStandings()

        assertEquals(expected, mockWebServer.takeRequest().path)
    }

    @Test
    fun `getConstructorStandings should return parsed data class on success`() = runTest {
        val expected = constructorStandingsWrapper
        MockResponse().apply {
            setBody("constructor_standings.json".readPath())
            mockWebServer.enqueue(this)
        }

        val result = service.getConstructorStandings()

        assertEquals(expected, result)
    }

    @Test
    fun `getConstructorStandings should send correct request path when called`() = runTest {
        val expected = "/current/constructorStandings.json"
        MockResponse().apply {
            setBody("constructor_standings.json".readPath())
            mockWebServer.enqueue(this)
        }

        service.getConstructorStandings()

        assertEquals(expected, mockWebServer.takeRequest().path)
    }
}