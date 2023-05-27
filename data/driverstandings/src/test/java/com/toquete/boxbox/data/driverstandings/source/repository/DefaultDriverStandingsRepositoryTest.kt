package com.toquete.boxbox.data.driverstandings.source.repository

import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.core.testing.data.driverEntities
import com.toquete.boxbox.core.testing.data.driverStandingEntities
import com.toquete.boxbox.core.testing.data.driverStandingsResponse
import com.toquete.boxbox.core.testing.data.fullDriverStandingEntities
import com.toquete.boxbox.core.testing.data.fullDriverStandings
import com.toquete.boxbox.data.constructors.source.local.ConstructorsLocalDataSource
import com.toquete.boxbox.data.drivers.source.local.DriversLocalDataSource
import com.toquete.boxbox.data.driverstandings.repository.DefaultDriverStandingsRepository
import com.toquete.boxbox.data.driverstandings.source.local.DriverStandingsLocalDataSource
import com.toquete.boxbox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertContentEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultDriverStandingsRepositoryTest {

    private val remoteDataSource: DriverStandingsRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: DriverStandingsLocalDataSource = mockk(relaxed = true)
    private val driversLocalDataSource: DriversLocalDataSource = mockk(relaxed = true)
    private val constructorsLocalDataSource: ConstructorsLocalDataSource = mockk(relaxed = true)

    private val repository = DefaultDriverStandingsRepository(
        remoteDataSource,
        localDataSource,
        driversLocalDataSource,
        constructorsLocalDataSource
    )

    @Test
    fun `getDriverStandings should return mapped list when called`() = runTest {
        coEvery { localDataSource.getDriverStandings() } returns flowOf(fullDriverStandingEntities)

        val result = repository.getDriverStandings()

        assertContentEquals(fullDriverStandings, result.first())
    }

    @Test
    fun `sync should return true when remote data is saved in database`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } returns driverStandingsResponse

        val result = repository.sync()

        assertTrue(result)
    }

    @Test
    fun `sync should insert driver data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } returns driverStandingsResponse

        repository.sync()

        coVerify { driversLocalDataSource.insertAll(driverEntities) }
    }

    @Test
    fun `sync should insert constructor data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } returns driverStandingsResponse

        repository.sync()

        coVerify { constructorsLocalDataSource.insertAll(constructorEntities) }
    }

    @Test
    fun `sync should insert driver standings data in database when remote data is gotten successfully`() = runTest {
        val expected = driverStandingEntities.map { it.copy(id = null) }
        coEvery { remoteDataSource.getDriverStandings() } returns driverStandingsResponse

        repository.sync()

        coVerify { localDataSource.insertAll(expected) }
    }

    @Test
    fun `sync should return false when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }

    @Test
    fun `sync should not call local data sources when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) {
            driversLocalDataSource.insertAll(any())
            localDataSource.insertAll(any())
            constructorsLocalDataSource.insertAll(any())
        }
    }

    @Test
    fun `sync should return false when drivers local data insertion returns error`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } returns driverStandingsResponse
        coEvery { driversLocalDataSource.insertAll(any()) } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }

    @Test
    fun `sync should return false when constructors local data insertion returns error`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } returns driverStandingsResponse
        coEvery { driversLocalDataSource.insertAll(any()) } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }

    @Test
    fun `sync should return false when drivers standings local data insertion returns error`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } returns driverStandingsResponse
        coEvery { localDataSource.insertAll(any()) } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }
}
