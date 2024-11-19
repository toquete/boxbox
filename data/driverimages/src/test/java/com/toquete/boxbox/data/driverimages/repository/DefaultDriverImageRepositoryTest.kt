package com.toquete.boxbox.data.driverimages.repository

import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.core.testing.data.driverImageEntities
import com.toquete.boxbox.core.testing.data.driverImageResponses
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class DefaultDriverImageRepositoryTest {

    private val remoteDataSource: DriverImageRemoteDataSource = mockk(relaxed = true)
    private val driverImageDao: DriverImageDao = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val repository = DefaultDriverImageRepository(remoteDataSource, driverImageDao, testDispatcher)

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getDriversImages() } returns driverImageResponses
        coEvery { driverImageDao.upsertAll(any()) } returns Unit

        repository.sync()

        coVerify {
            remoteDataSource.getDriversImages()
            driverImageDao.upsertAll(driverImageEntities)
        }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getDriversImages() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { driverImageDao.upsertAll(any()) }
    }
}
