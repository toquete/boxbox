package com.toquete.boxbox.data.races.source.remote.source.repository

import com.toquete.boxbox.core.testing.data.circuitEntities
import com.toquete.boxbox.core.testing.data.raceEntities
import com.toquete.boxbox.core.testing.data.races
import com.toquete.boxbox.core.testing.data.racesResponse
import com.toquete.boxbox.core.testing.data.racesWithCircuits
import com.toquete.boxbox.data.circuits.source.local.CircuitLocalDataSource
import com.toquete.boxbox.data.races.repository.DefaultRaceRepository
import com.toquete.boxbox.data.races.source.local.RaceLocalDataSource
import com.toquete.boxbox.data.races.source.remote.RaceRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertContentEquals

class DefaultRaceRepositoryTest {

    private val remoteDataSource: RaceRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: RaceLocalDataSource = mockk(relaxed = true)
    private val circuitLocalDataSource: CircuitLocalDataSource = mockk(relaxed = true)
    private val repository = DefaultRaceRepository(
        remoteDataSource,
        localDataSource,
        circuitLocalDataSource
    )

    @Test
    fun `getRacesBySeason should return mapped list when called`() = runTest {
        coEvery { localDataSource.getRacesBySeason(any()) } returns flowOf(racesWithCircuits)

        val result = repository.getRacesBySeason(season = "2023")

        assertContentEquals(races, result.first())
    }

    @Test
    fun `sync should insert circuit data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getRaces() } returns racesResponse

        repository.sync()

        coEvery { circuitLocalDataSource.insertAll(circuitEntities.take(1)) }
    }

    @Test
    fun `sync should insert race data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getRaces() } returns racesResponse

        repository.sync()

        coEvery { localDataSource.insertAll(raceEntities.take(1)) }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data sources when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getRaces() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) {
            circuitLocalDataSource.insertAll(any())
            localDataSource.insertAll(any())
        }
    }
}
