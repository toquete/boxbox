package com.toquete.boxbox.data.raceresults.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.testing.data.raceResultWrapper
import com.toquete.boxbox.core.testing.data.raceResultsResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultRaceResultRemoteDataSourceTest {

    private val service: BoxBoxService = mockk(relaxed = true)
    private val dataSource = DefaultRaceResultRemoteDataSource(service)

    @Test
    fun `getRaceResults should return race results`() = runTest {
        coEvery { service.getRaceResults() } returns raceResultWrapper

        val result = dataSource.getRaceResults()

        assertContentEquals(raceResultsResponse, result)
    }
}
