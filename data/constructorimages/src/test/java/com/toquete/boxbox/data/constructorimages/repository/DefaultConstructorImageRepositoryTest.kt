package com.toquete.boxbox.data.constructorimages.repository

import com.toquete.boxbox.core.testing.data.constructorImageEntities
import com.toquete.boxbox.core.testing.data.constructorImageResponses
import com.toquete.boxbox.data.constructorimages.source.local.ConstructorImageLocalDataSource
import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class DefaultConstructorImageRepositoryTest {

    private val remoteDataSource: ConstructorImageRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: ConstructorImageLocalDataSource = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val repository = DefaultConstructorImageRepository(remoteDataSource, localDataSource, testDispatcher)

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getConstructorsImages() } returns constructorImageResponses
        coEvery { localDataSource.insertAll(any()) } returns Unit

        repository.sync()

        coVerify {
            remoteDataSource.getConstructorsImages()
            localDataSource.insertAll(constructorImageEntities)
        }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getConstructorsImages() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { localDataSource.insertAll(any()) }
    }
}
