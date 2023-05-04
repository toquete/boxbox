package com.toquete.boxbox.data.fulldriverstandings.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.testing.data.driverStandingsResponse
import com.toquete.boxbox.core.testing.data.driverStandingsWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultFullDriverStandingsRemoteDataSourceTest {

    private val service: BoxBoxService = mockk(relaxed = true)
    private val dataSource = DefaultFullDriverStandingsRemoteDataSource(service)

    @Test
    fun `getFullDriverStandings should return full driver standings when called`() = runTest {
        coEvery { service.getDriverStandings() } returns driverStandingsWrapper

        val result = dataSource.getFullDriverStandings()

        assertContentEquals(driverStandingsResponse, result)
    }
}
