package com.toquete.boxbox.data.drivers

import com.toquete.boxbox.core.testing.data.driverEntities
import com.toquete.boxbox.data.drivers.source.local.DefaultDriversLocalDataSource
import com.toquete.boxbox.database.dao.DriverDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultDriversLocalDataSourceTest {

    private val driverDao: DriverDao = mockk()
    private val dataSource = DefaultDriversLocalDataSource(driverDao)

    @Test
    fun `insertAll should insert drivers when called`() = runTest {
        coEvery { driverDao.insertAll(any()) } returns Unit

        dataSource.insertAll(driverEntities)

        coVerify { dataSource.insertAll(driverEntities) }
    }
}