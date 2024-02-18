package com.toquete.boxbox.data.raceresults.repository

import com.toquete.boxbox.core.testing.data.raceResultEntities
import com.toquete.boxbox.core.testing.data.raceResults
import com.toquete.boxbox.core.testing.data.raceResultsResponse
import com.toquete.boxbox.core.testing.data.raceResultsWithDriverAndConstructor
import com.toquete.boxbox.data.raceresults.source.local.RaceResultLocalDataSource
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertContentEquals

class DefaultRaceResultRepositoryTest {

    private val remoteDataSource: RaceResultRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: RaceResultLocalDataSource = mockk(relaxed = true)
    private val repository = DefaultRaceResultRepository(remoteDataSource, localDataSource)

    @Test
    fun `getRaceResultsBySeason should return mapped list when called`() = runTest {
        coEvery { localDataSource.getRaceResultsBySeason(any()) } returns flowOf(raceResultsWithDriverAndConstructor)

        val result = repository.getRaceResultsBySeason(season = "2023").first()

        assertContentEquals(raceResults, result)
    }

    @Test
    fun `sync should insert race result data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getRaceResults() } returns raceResultsResponse

        repository.sync()

        coEvery { localDataSource.insertAll(raceResultEntities) }
    }

    @Test
    fun `sync should insert empty data in database when remote data is gotten successfully`() = runTest {
        val data = raceResultsResponse.map { it.copy(results = null) }
        coEvery { remoteDataSource.getRaceResults() } returns data

        repository.sync()

        coEvery { localDataSource.insertAll(emptyList()) }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getRaceResults() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) {
            localDataSource.insertAll(any())
        }
    }
}
