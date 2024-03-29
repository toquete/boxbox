package com.toquete.boxbox.data.constructorstandings.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.testing.data.constructorStandingsResponse
import com.toquete.boxbox.core.testing.data.constructorStandingsWrapper
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
}
