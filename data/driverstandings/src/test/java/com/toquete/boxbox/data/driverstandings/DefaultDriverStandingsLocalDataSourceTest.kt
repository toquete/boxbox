package com.toquete.boxbox.data.driverstandings

import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.testing.data.driverStandingEntities
import com.toquete.boxbox.core.testing.data.newFullDriverStandingEntities
import com.toquete.boxbox.data.driverstandings.source.local.DefaultDriverStandingsLocalDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultDriverStandingsLocalDataSourceTest {

    private val driverStandingDao: DriverStandingDao = mockk(relaxed = true)
    private val dataSource = DefaultDriverStandingsLocalDataSource(driverStandingDao)

    @Test
    fun `insertAll should insert driver standings when called`() = runTest {
        coEvery { driverStandingDao.insertAll(any()) } returns Unit

        dataSource.insertAll(driverStandingEntities)

        coVerify { driverStandingDao.deleteAndInsertInTransaction(driverStandingEntities) }
    }

    @Test
    fun `getDriverStandings should return driver standings when called`() = runTest {
        coEvery { driverStandingDao.getFullDriverStandings() } returns flowOf(newFullDriverStandingEntities)

        val result = dataSource.getDriverStandings().first()

        assertContentEquals(newFullDriverStandingEntities, result)
    }
}
