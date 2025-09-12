package com.toquete.boxbox.data.constructorstandings.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.data.constructorstandings.fake.FakeBoxBoxService
import com.toquete.boxbox.data.constructorstandings.mock.constructorStandingsResponse
import com.toquete.boxbox.data.constructorstandings.mock.constructorStandingsWrapper
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DefaultConstructorStandingsRemoteDataSourceTest {

    private lateinit var service: BoxBoxService
    private lateinit var dataSource: DefaultConstructorStandingsRemoteDataSource

    @Test
    fun `getConstructorStandings should return full constructor standings when called`() = runTest {
        setUp()

        val result = dataSource.getConstructorStandings()

        assertContentEquals(constructorStandingsResponse, result)
    }

    @Test
    fun `getConstructorStandings should return empty list when standings list is empty`() = runTest {
        setUp(
            constructorStandings = constructorStandingsWrapper.copy(
                data = constructorStandingsWrapper.data.copy(
                    standingTable = constructorStandingsWrapper.data.standingTable.copy(
                        standingsLists = emptyList()
                    )
                )
            )
        )

        val result = dataSource.getConstructorStandings()

        assertContentEquals(emptyList(), result)
    }

    private fun setUp(constructorStandings: ConstructorStandingsWrapper = constructorStandingsWrapper) {
        service = FakeBoxBoxService(constructorStandings)
        dataSource = DefaultConstructorStandingsRemoteDataSource(service)
    }
}
