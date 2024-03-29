package com.toquete.boxbox.data.races.source.local

import com.toquete.boxbox.core.database.dao.RaceDao
import com.toquete.boxbox.core.testing.data.raceEntities
import com.toquete.boxbox.core.testing.data.racesWithCircuits
import com.toquete.boxbox.data.races.source.local.DefaultRaceLocalDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultRaceLocalDataSourceTest {

    private val dao: RaceDao = mockk(relaxed = true)
    private val dataSource = DefaultRaceLocalDataSource(dao)

    @Test
    fun `getRacesBySeason should return races`() = runTest {
        every { dao.getRacesBySeason(any()) } returns flowOf(racesWithCircuits)

        val result = dataSource.getRacesBySeason(season = "2023").first()

        assertContentEquals(racesWithCircuits, result)
    }

    @Test
    fun `insertAll should insert races`() = runTest {
        coEvery { dao.upsertAll(any()) } returns Unit

        dataSource.insertAll(raceEntities)

        coVerify { dao.upsertAll(raceEntities) }
    }
}
