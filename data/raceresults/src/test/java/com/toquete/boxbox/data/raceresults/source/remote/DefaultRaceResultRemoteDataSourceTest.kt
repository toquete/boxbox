package com.toquete.boxbox.data.raceresults.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.data.raceresults.mock.raceResultWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class DefaultRaceResultRemoteDataSourceTest {

    private val service: BoxBoxService = mockk(relaxed = true)
    private val dataSource = DefaultRaceResultRemoteDataSource(service)

    @Test
    fun `getRaceResults should return race results`() = runTest {
        val offset = 0
        coEvery { service.getRaceResults(offset) } returns raceResultWrapper

        val result = dataSource.getRaceResults(offset)

        assertEquals(raceResultWrapper, result)
    }
}
