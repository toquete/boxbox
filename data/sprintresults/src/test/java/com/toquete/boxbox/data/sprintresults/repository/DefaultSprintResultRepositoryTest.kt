package com.toquete.boxbox.data.sprintresults.repository

import com.toquete.boxbox.core.network.model.RaceDataResponse
import com.toquete.boxbox.core.network.model.RaceTableResponse
import com.toquete.boxbox.core.testing.data.sprintRaceResultEntities
import com.toquete.boxbox.core.testing.data.sprintRaceResultWrapper
import com.toquete.boxbox.core.testing.data.sprintRaceResults
import com.toquete.boxbox.core.testing.data.sprintRaceResultsResponse
import com.toquete.boxbox.core.testing.data.sprintRaceResultsWithDriverAndConstructor
import com.toquete.boxbox.data.sprintresults.source.local.SprintResultLocalDataSource
import com.toquete.boxbox.data.sprintresults.source.remote.SprintResultRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertContentEquals

class DefaultSprintResultRepositoryTest {

    private val remoteDataSource: SprintResultRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: SprintResultLocalDataSource = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val repository = DefaultSprintResultRepository(remoteDataSource, localDataSource, testDispatcher)

    @Test
    fun `getSprintResultsBySeasonAndRound should return mapped list when called`() = runTest {
        coEvery {
            localDataSource.getSprintResultsBySeasonAndRound(any(), any())
        } returns flowOf(sprintRaceResultsWithDriverAndConstructor)

        val result = repository.getSprintResultsBySeasonAndRound(season = "2023", round = 1).first()

        assertContentEquals(sprintRaceResults, result)
    }

    @Test
    fun `sync should insert sprint race result data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getSprintResults(any()) } returns sprintRaceResultWrapper

        repository.sync()

        coEvery { localDataSource.insertAll(sprintRaceResultEntities) }
    }

    @Test
    fun `sync should insert empty data in database when remote data is gotten successfully`() = runTest {
        val sprintRaceResults = sprintRaceResultsResponse.map { it.copy(results = null) }
        val data = sprintRaceResultWrapper.copy(
            data = RaceTableResponse(
                totalPages = 100,
                raceTable = RaceDataResponse(
                    season = "2024",
                    races = sprintRaceResults
                )
            )
        )
        coEvery { remoteDataSource.getSprintResults(any()) } returns data

        repository.sync()

        coEvery { localDataSource.insertAll(emptyList()) }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getSprintResults(any()) } throws IOException()

        repository.sync()

        coVerify(exactly = 0) {
            localDataSource.insertAll(any())
        }
    }
}
