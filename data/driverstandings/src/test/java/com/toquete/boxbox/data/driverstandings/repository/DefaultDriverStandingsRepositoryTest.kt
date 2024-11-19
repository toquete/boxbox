package com.toquete.boxbox.data.driverstandings.repository

import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.core.testing.data.driverEntities
import com.toquete.boxbox.core.testing.data.driverStandingEntities
import com.toquete.boxbox.core.testing.data.driverStandings
import com.toquete.boxbox.core.testing.data.driverStandingsResponse
import com.toquete.boxbox.core.testing.data.fullDriverStandingEntities
import com.toquete.boxbox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
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

class DefaultDriverStandingsRepositoryTest {

    private val remoteDataSource: DriverStandingsRemoteDataSource = mockk(relaxed = true)
    private val driverStandingDao = mockk<DriverStandingDao>(relaxed = true)
    private val driverDao = mockk<DriverDao>(relaxed = true)
    private val constructorDao = mockk<ConstructorDao>(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()

    private val repository = DefaultDriverStandingsRepository(
        remoteDataSource,
        driverStandingDao,
        driverDao,
        constructorDao,
        testDispatcher
    )

    @Test
    fun `getDriverStandings should return mapped list when called`() = runTest {
        coEvery { driverStandingDao.getFullDriverStandings() } returns flowOf(fullDriverStandingEntities)

        val result = repository.getDriverStandings()

        assertContentEquals(driverStandings, result.first())
    }

    @Test
    fun `sync should insert driver data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } returns driverStandingsResponse

        repository.sync()

        coVerify { driverDao.upsertAll(driverEntities) }
    }

    @Test
    fun `sync should insert constructor data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } returns driverStandingsResponse

        repository.sync()

        coVerify { constructorDao.upsertAll(constructorEntities) }
    }

    @Test
    fun `sync should insert driver standings data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } returns driverStandingsResponse

        repository.sync()

        coVerify { driverStandingDao.insertAll(driverStandingEntities) }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data sources when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getDriverStandings() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) {
            driverDao.upsertAll(any())
            driverStandingDao.insertAll(any())
            constructorDao.upsertAll(any())
        }
    }
}
