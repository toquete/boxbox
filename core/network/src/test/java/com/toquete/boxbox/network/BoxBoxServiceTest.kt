package com.toquete.boxbox.network

import com.toquete.boxbox.network.di.NetworkModule
import com.toquete.boxbox.network.extension.readPath
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
        val expected = FakeRemoteData.driverStandingsResponse
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
}