package com.toquete.boxbox.data.driverimages.source.local

import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.core.testing.data.driverImageEntities
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultDriverImageLocalDataSourceTest {

    private val driverImageDao: DriverImageDao = mockk(relaxed = true)
    private val dataSource = DefaultDriverImageLocalDataSource(driverImageDao)

    @Test
    fun `insertAll should insert all drivers images when called`() = runTest {
        coEvery { driverImageDao.upsertAll(any()) } returns Unit

        dataSource.insertAll(driverImageEntities)

        coVerify { driverImageDao.upsertAll(driverImageEntities) }
    }
}
