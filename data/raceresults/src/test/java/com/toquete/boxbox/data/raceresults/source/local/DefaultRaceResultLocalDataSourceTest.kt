package com.toquete.boxbox.data.raceresults.source.local

import com.toquete.boxbox.core.database.dao.RaceResultDao
import com.toquete.boxbox.core.testing.data.raceResultEntities
import com.toquete.boxbox.core.testing.data.raceResultsWithDriverAndConstructor
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultRaceResultLocalDataSourceTest {

    private val dao: RaceResultDao = mockk(relaxed = true)
    private val dataSource = DefaultRaceResultLocalDataSource(dao)

    @Test
    fun `getRaceResultsBySeason should return race results`() = runTest {
        every { dao.getRaceResultsBySeason(any()) } returns flowOf(raceResultsWithDriverAndConstructor)

        val result = dataSource.getRaceResultsBySeason(season = "2023").first()

        assertContentEquals(raceResultsWithDriverAndConstructor, result)
    }

    @Test
    fun `insertAll should insert races`() = runTest {
        coEvery { dao.upsertAll(any()) } returns Unit

        dataSource.insertAll(raceResultEntities)

        coVerify { dao.upsertAll(raceResultEntities) }
    }
}
