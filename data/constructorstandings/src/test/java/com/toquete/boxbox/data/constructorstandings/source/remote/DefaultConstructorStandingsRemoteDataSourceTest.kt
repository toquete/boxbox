package com.toquete.boxbox.data.constructorstandings.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.data.constructorstandings.mock.constructorStandingsResponse
import com.toquete.boxbox.data.constructorstandings.mock.constructorStandingsWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultConstructorStandingsRemoteDataSourceTest {

    private val service: BoxBoxService = mockk(relaxed = true)
    private val dataSource = DefaultConstructorStandingsRemoteDataSource(service)

    @Test
    fun `getConstructorStandings should return full constructor standings when called`() = runTest {
        coEvery { service.getConstructorStandings() } returns constructorStandingsWrapper

        val result = dataSource.getConstructorStandings()

        assertContentEquals(constructorStandingsResponse, result)
    }

    @Test
    fun `getConstructorStandings should return empty list when standings list is empty`() = runTest {
        coEvery { service.getConstructorStandings() } returns constructorStandingsWrapper.copy(
            data = constructorStandingsWrapper.data.copy(
                standingTable = constructorStandingsWrapper.data.standingTable.copy(
                    standingsLists = emptyList()
                )
            )
        )

        val result = dataSource.getConstructorStandings()

        assertContentEquals(emptyList(), result)
    }
}
