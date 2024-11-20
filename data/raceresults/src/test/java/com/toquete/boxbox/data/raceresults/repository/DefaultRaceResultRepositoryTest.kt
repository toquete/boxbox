package com.toquete.boxbox.data.raceresults.repository

import com.toquete.boxbox.core.database.dao.RaceResultDao
import com.toquete.boxbox.core.network.model.RaceDataResponse
import com.toquete.boxbox.core.network.model.RaceTableResponse
import com.toquete.boxbox.core.testing.data.raceResultEntities
import com.toquete.boxbox.core.testing.data.raceResultWrapper
import com.toquete.boxbox.core.testing.data.raceResults
import com.toquete.boxbox.core.testing.data.raceResultsResponse
import com.toquete.boxbox.core.testing.data.raceResultsWithDriverAndConstructor
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
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

class DefaultRaceResultRepositoryTest {

    private val remoteDataSource: RaceResultRemoteDataSource = mockk(relaxed = true)
    private val raceResultDao: RaceResultDao = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val repository = DefaultRaceResultRepository(remoteDataSource, raceResultDao, testDispatcher)

    @Test
    fun `getRaceResultsBySeason should return mapped list when called`() = runTest {
        coEvery {
            raceResultDao.getRaceResultsBySeasonAndRound(any(), any())
        } returns flowOf(raceResultsWithDriverAndConstructor)

        val result = repository.getRaceResultsBySeasonAndRound(season = "2023", round = 1).first()

        assertContentEquals(raceResults, result)
    }

    @Test
    fun `sync should insert race result data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getRaceResults(any()) } returns raceResultWrapper

        repository.sync()

        coEvery { raceResultDao.upsertAll(raceResultEntities) }
    }

    @Test
    fun `sync should insert empty data in database when remote data is gotten successfully`() = runTest {
        val raceResults = raceResultsResponse.map { it.copy(results = null) }
        val data = raceResultWrapper.copy(
            data = RaceTableResponse(
                totalPages = 100,
                raceTable = RaceDataResponse(
                    season = "2024",
                    races = raceResults
                )
            )
        )
        coEvery { remoteDataSource.getRaceResults(any()) } returns data

        repository.sync()

        coEvery { raceResultDao.upsertAll(emptyList()) }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getRaceResults(any()) } throws IOException()

        repository.sync()

        coVerify(exactly = 0) {
            raceResultDao.upsertAll(any())
        }
    }
}
