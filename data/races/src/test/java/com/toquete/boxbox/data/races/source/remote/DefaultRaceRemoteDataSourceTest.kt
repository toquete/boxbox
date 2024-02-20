package com.toquete.boxbox.data.races.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.testing.data.racesResponse
import com.toquete.boxbox.core.testing.data.racesWrapper
import com.toquete.boxbox.data.races.source.remote.DefaultRaceRemoteDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultRaceRemoteDataSourceTest {

    private val service: BoxBoxService = mockk(relaxed = true)
    private val dataSource = DefaultRaceRemoteDataSource(service)

    @Test
    fun `getRaces should return races`() = runTest {
        coEvery { service.getRaces() } returns racesWrapper

        val result = dataSource.getRaces()

        assertContentEquals(racesResponse, result)
    }
}
