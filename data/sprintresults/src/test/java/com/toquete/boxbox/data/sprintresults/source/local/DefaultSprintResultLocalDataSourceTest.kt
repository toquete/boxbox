package com.toquete.boxbox.data.sprintresults.source.local

import com.toquete.boxbox.core.database.dao.SprintRaceResultDao
import com.toquete.boxbox.core.testing.data.sprintRaceResultEntities
import com.toquete.boxbox.core.testing.data.sprintRaceResultsWithDriverAndConstructor
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultSprintResultLocalDataSourceTest {

    private val dao: SprintRaceResultDao = mockk(relaxed = true)
    private val dataSource = DefaultSprintResultLocalDataSource(dao)

    @Test
    fun `getSprintRaceResultsBySeasonAndRound should return sprint race results`() = runTest {
        every { dao.getSprintRaceResultsBySeasonAndRound(any(), any()) } returns flowOf(
            sprintRaceResultsWithDriverAndConstructor
        )

        val result = dataSource.getSprintResultsBySeasonAndRound(season = "2023", round = 1).first()

        assertContentEquals(sprintRaceResultsWithDriverAndConstructor, result)
    }

    @Test
    fun `insertAll should insert sprint races`() = runTest {
        coEvery { dao.upsertAll(any()) } returns Unit

        dataSource.insertAll(sprintRaceResultEntities)

        coVerify { dao.upsertAll(sprintRaceResultEntities) }
    }
}
