package com.toquete.boxbox.data.raceresults.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.data.raceresults.fake.FakeBoxBoxService
import com.toquete.boxbox.data.raceresults.mock.raceResultWrapper
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class DefaultRaceResultRemoteDataSourceTest {

    private lateinit var service: BoxBoxService
    private lateinit var dataSource: DefaultRaceResultRemoteDataSource

    @Before
    fun setUp() {
        service = FakeBoxBoxService()
        dataSource = DefaultRaceResultRemoteDataSource(service)
    }

    @Test
    fun `getRaceResults should return race results`() = runTest {
        val offset = 0

        val result = dataSource.getRaceResults(offset)

        assertEquals(raceResultWrapper, result)
    }
}
