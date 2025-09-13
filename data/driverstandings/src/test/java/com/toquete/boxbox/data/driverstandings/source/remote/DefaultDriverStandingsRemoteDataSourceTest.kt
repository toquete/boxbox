package com.toquete.boxbox.data.driverstandings.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.data.driverstandings.mock.driverStandingsResponse
import com.toquete.boxbox.data.driverstandings.mock.driverStandingsWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultDriverStandingsRemoteDataSourceTest {

    private val service: BoxBoxService = mockk(relaxed = true)
    private val dataSource = DefaultDriverStandingsRemoteDataSource(service)

    @Test
    fun `getFullDriverStandings should return full driver standings when called`() = runTest {
        coEvery { service.getDriverStandings() } returns driverStandingsWrapper

        val result = dataSource.getDriverStandings()

        assertContentEquals(driverStandingsResponse, result)
    }

    @Test
    fun `getFullDriverStandings should return empty list when standings list is empty`() = runTest {
        coEvery { service.getDriverStandings() } returns driverStandingsWrapper.copy(
            data = driverStandingsWrapper.data.copy(
                standingTable = driverStandingsWrapper.data.standingTable.copy(
                    standingsLists = emptyList()
                )
            )
        )

        val result = dataSource.getDriverStandings()

        assertContentEquals(emptyList(), result)
    }
}
