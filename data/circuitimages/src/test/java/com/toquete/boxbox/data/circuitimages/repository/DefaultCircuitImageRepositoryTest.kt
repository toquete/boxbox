package com.toquete.boxbox.data.circuitimages.repository

import com.toquete.boxbox.core.database.dao.CircuitImageDao
import com.toquete.boxbox.core.testing.data.circuitImageEntities
import com.toquete.boxbox.core.testing.data.circuitImageResponses
import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class DefaultCircuitImageRepositoryTest {

    private val remoteDataSource: CircuitImageRemoteDataSource = mockk(relaxed = true)
    private val circuitImageDao: CircuitImageDao = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val repository = DefaultCircuitImageRepository(remoteDataSource, circuitImageDao, testDispatcher)

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getCircuitImages() } returns circuitImageResponses
        coEvery { circuitImageDao.upsertAll(any()) } returns Unit

        repository.sync()

        coVerify {
            remoteDataSource.getCircuitImages()
            circuitImageDao.upsertAll(circuitImageEntities)
        }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getCircuitImages() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { circuitImageDao.upsertAll(any()) }
    }
}
