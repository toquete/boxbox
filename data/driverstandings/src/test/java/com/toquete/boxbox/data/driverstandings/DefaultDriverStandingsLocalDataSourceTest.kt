package com.toquete.boxbox.data.driverstandings

import com.toquete.boxbox.core.testing.data.driverStandingEntities
import com.toquete.boxbox.data.driverstandings.source.local.DefaultDriverStandingsLocalDataSource
import com.toquete.boxbox.database.dao.DriverStandingDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultDriverStandingsLocalDataSourceTest {

    private val driverStandingDao: DriverStandingDao = mockk(relaxed = true)
    private val dataSource = DefaultDriverStandingsLocalDataSource(driverStandingDao)

    @Test
    fun `insertAll should insert driver standings when called`() = runTest {
        coEvery { driverStandingDao.insertAll(any()) } returns Unit

        dataSource.insertAll(driverStandingEntities)

        coVerify { driverStandingDao.deleteAndInsertInTransaction(driverStandingEntities) }
    }
}