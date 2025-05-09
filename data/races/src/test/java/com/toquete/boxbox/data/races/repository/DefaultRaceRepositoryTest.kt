package com.toquete.boxbox.data.races.repository

import com.toquete.boxbox.core.database.dao.CircuitDao
import com.toquete.boxbox.core.database.dao.RaceDao
import com.toquete.boxbox.core.testing.data.circuitEntities
import com.toquete.boxbox.core.testing.data.raceEntities
import com.toquete.boxbox.core.testing.data.races
import com.toquete.boxbox.core.testing.data.racesResponse
import com.toquete.boxbox.core.testing.data.racesWithCircuits
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
    private val raceDao: RaceDao = mockk(relaxed = true)
    private val circuitDao: CircuitDao = mockk(relaxed = true)
    private val repository = DefaultRaceRepository(
        remoteDataSource,
        raceDao,
        circuitDao
    )

    @Test
    fun `getUpcomingRacesBySeason should return mapped list when called`() = runTest {
        coEvery { raceDao.getUpcomingRacesBySeason(any(), any()) } returns flowOf(racesWithCircuits)

        val result = repository.getUpcomingRacesBySeason(season = "2023", today = "2023-01-01")

        assertContentEquals(races, result.first())
    }

    @Test
    fun `getPastRacesBySeason should return mapped list when called`() = runTest {
        coEvery { raceDao.getPastRacesBySeason(any(), any()) } returns flowOf(racesWithCircuits)

        val result = repository.getPastRacesBySeason(season = "2023", today = "2023-01-01")

        assertContentEquals(races, result.first())
    }

    @Test
    fun `sync should insert circuit data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getRaces() } returns racesResponse

        repository.sync()

        coEvery { circuitDao.upsertAll(circuitEntities.take(1)) }
    }

    @Test
    fun `sync should insert race data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getRaces() } returns racesResponse

        repository.sync()

        coEvery { raceDao.upsertAll(raceEntities.take(1)) }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data sources when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getRaces() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) {
            circuitDao.upsertAll(any())
            raceDao.upsertAll(any())
        }
    }
}
