package com.toquete.boxbox.data.driverimages.repository

import com.toquete.boxbox.core.testing.data.driverImageEntities
import com.toquete.boxbox.core.testing.data.driverImageResponses
import com.toquete.boxbox.data.driverimages.source.local.DriverImageLocalDataSource
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultDriverImageRepositoryTest {

    private val remoteDataSource: DriverImageRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: DriverImageLocalDataSource = mockk(relaxed = true)
    private val repository = DefaultDriverImageRepository(remoteDataSource, localDataSource)

    @Test
    fun `sync should return true when remote data is saved in database`() = runTest {
        coEvery { remoteDataSource.getDriversImages() } returns driverImageResponses
        coEvery { localDataSource.insertAll(any()) } returns Unit

        val result = repository.sync()

        assertTrue(result)
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getDriversImages() } returns driverImageResponses
        coEvery { localDataSource.insertAll(any()) } returns Unit

        repository.sync()

        coVerify { localDataSource.insertAll(driverImageEntities) }
    }

    @Test
    fun `sync should return false when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getDriversImages() } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }

    @Test
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getDriversImages() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { localDataSource.insertAll(any()) }
    }

    @Test
    fun `sync should return false when local data insertion returns error`() = runTest {
        coEvery { remoteDataSource.getDriversImages() } returns driverImageResponses
        coEvery { localDataSource.insertAll(any()) } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }
}
