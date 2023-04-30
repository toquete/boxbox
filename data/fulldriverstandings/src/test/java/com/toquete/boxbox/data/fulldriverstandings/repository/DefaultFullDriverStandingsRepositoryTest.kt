package com.toquete.boxbox.data.fulldriverstandings.repository

import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.core.testing.data.driverEntities
import com.toquete.boxbox.core.testing.data.driverStandingEntities
import com.toquete.boxbox.core.testing.data.driverStandingsResponse
import com.toquete.boxbox.core.testing.data.fullDriverStandingEntities
import com.toquete.boxbox.core.testing.data.fullDriverStandings
import com.toquete.boxbox.data.constructors.source.local.ConstructorsLocalDataSource
import com.toquete.boxbox.data.drivers.source.local.DriversLocalDataSource
import com.toquete.boxbox.data.driverstandings.source.local.DriverStandingsLocalDataSource
import com.toquete.boxbox.data.fulldriverstandings.source.local.FullDriverStandingsLocalDataSource
import com.toquete.boxbox.data.fulldriverstandings.source.remote.FullDriverStandingsRemoteDataSource
import com.toquete.boxbox.database.model.ConstructorEntity
import com.toquete.boxbox.database.model.DriverEntity
import com.toquete.boxbox.database.model.DriverStandingEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertContentEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultFullDriverStandingsRepositoryTest {

    private val remoteDataSource: FullDriverStandingsRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: FullDriverStandingsLocalDataSource = mockk(relaxed = true)
    private val driverStandingsLocalDataSource: DriverStandingsLocalDataSource = mockk(relaxed = true)
    private val driversLocalDataSource: DriversLocalDataSource = mockk(relaxed = true)
    private val constructorsLocalDataSource: ConstructorsLocalDataSource = mockk(relaxed = true)

    private val repository = DefaultFullDriverStandingsRepository(
        remoteDataSource,
        localDataSource,
        driverStandingsLocalDataSource,
        driversLocalDataSource,
        constructorsLocalDataSource
    )

    @Test
    fun `getFullDriverStandings should return mapped list when called`() = runTest {
        coEvery { localDataSource.getFullDriverStandings() } returns flowOf(fullDriverStandingEntities)

        val result = repository.getFullDriverStandings()

        assertContentEquals(fullDriverStandings, result.first())
    }

    @Test
    fun `sync should return true when remote data is saved in database`() = runTest {
        coEvery { remoteDataSource.getFullDriverStandings() } returns driverStandingsResponse

        val result = repository.sync()

        assertTrue(result)
    }

    @Test
    fun `sync should insert driver data in database when remote data is gotten successfully`() = runTest {
        val slot = slot<List<DriverEntity>>()
        coEvery { remoteDataSource.getFullDriverStandings() } returns driverStandingsResponse

        repository.sync()

        coVerify { driversLocalDataSource.insertAll(capture(slot)) }

        assertContentEquals(driverEntities, slot.captured)
    }

    @Test
    fun `sync should insert constructor data in database when remote data is gotten successfully`() = runTest {
        val slot = slot<List<ConstructorEntity>>()
        coEvery { remoteDataSource.getFullDriverStandings() } returns driverStandingsResponse

        repository.sync()

        coVerify { constructorsLocalDataSource.insertAll(capture(slot)) }

        assertContentEquals(constructorEntities, slot.captured)
    }

    @Test
    fun `sync should insert driver standings data in database when remote data is gotten successfully`() = runTest {
        val slot = slot<List<DriverStandingEntity>>()
        coEvery { remoteDataSource.getFullDriverStandings() } returns driverStandingsResponse

        repository.sync()

        coVerify { driverStandingsLocalDataSource.insertAll(capture(slot)) }

        assertContentEquals(driverStandingEntities, slot.captured)
    }

    @Test
    fun `sync should return false when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getFullDriverStandings() } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }

    @Test
    fun `sync should not call local data sources when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getFullDriverStandings() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) {
            driversLocalDataSource.insertAll(any())
            driverStandingsLocalDataSource.insertAll(any())
            constructorsLocalDataSource.insertAll(any())
        }
    }

    @Test
    fun `sync should return false when drivers local data insertion returns error`() = runTest {
        coEvery { remoteDataSource.getFullDriverStandings() } returns driverStandingsResponse
        coEvery { driversLocalDataSource.insertAll(any()) } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }

    @Test
    fun `sync should return false when constructors local data insertion returns error`() = runTest {
        coEvery { remoteDataSource.getFullDriverStandings() } returns driverStandingsResponse
        coEvery { driversLocalDataSource.insertAll(any()) } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }

    @Test
    fun `sync should return false when drivers standings local data insertion returns error`() = runTest {
        coEvery { remoteDataSource.getFullDriverStandings() } returns driverStandingsResponse
        coEvery { driverStandingsLocalDataSource.insertAll(any()) } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }
}