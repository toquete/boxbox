package com.toquete.boxbox.data.sprintresults.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.testing.data.sprintRaceResultWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class DefaultSprintResultRemoteDataSourceTest {

    private val service: BoxBoxService = mockk(relaxed = true)
    private val dataSource = DefaultSprintResultRemoteDataSource(service)

    @Test
    fun `getSprintResults should return sprint race results`() = runTest {
        val offset = 0
        coEvery { service.getSprintRaceResults(offset) } returns sprintRaceResultWrapper

        val result = dataSource.getSprintResults(offset)

        assertEquals(sprintRaceResultWrapper, result)
    }
}
