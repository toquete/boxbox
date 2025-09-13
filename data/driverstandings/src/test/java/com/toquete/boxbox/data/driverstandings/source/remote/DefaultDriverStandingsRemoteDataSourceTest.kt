package com.toquete.boxbox.data.driverstandings.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.data.driverstandings.fake.FakeBoxBoxService
import com.toquete.boxbox.data.driverstandings.mock.driverStandingsResponse
import com.toquete.boxbox.data.driverstandings.mock.driverStandingsWrapper
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultDriverStandingsRemoteDataSourceTest {

    private lateinit var service: BoxBoxService
    private lateinit var dataSource: DefaultDriverStandingsRemoteDataSource

    @Test
    fun `getFullDriverStandings should return full driver standings when called`() = runTest {
        setUp()

        val result = dataSource.getDriverStandings()

        assertContentEquals(driverStandingsResponse, result)
    }

    @Test
    fun `getFullDriverStandings should return empty list when standings list is empty`() = runTest {
        setUp(
            driverStandings = driverStandingsWrapper.copy(
                data = driverStandingsWrapper.data.copy(
                    standingTable = driverStandingsWrapper.data.standingTable.copy(
                        standingsLists = emptyList()
                    )
                )
            )
        )

        val result = dataSource.getDriverStandings()

        assertContentEquals(emptyList(), result)
    }

    private fun setUp(driverStandings: DriverStandingsWrapper = driverStandingsWrapper) {
        service = FakeBoxBoxService(driverStandings)
        dataSource = DefaultDriverStandingsRemoteDataSource(service)
    }
}
