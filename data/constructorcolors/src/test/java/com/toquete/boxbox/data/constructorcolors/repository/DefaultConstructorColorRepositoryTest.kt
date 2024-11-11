package com.toquete.boxbox.data.constructorcolors.repository

import com.toquete.boxbox.core.testing.data.constructorColorEntities
import com.toquete.boxbox.core.testing.data.constructorColorResponses
import com.toquete.boxbox.data.constructorcolors.source.local.ConstructorColorLocalDataSource
import com.toquete.boxbox.data.constructorcolors.source.remote.ConstructorColorRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

internal class DefaultConstructorColorRepositoryTest {

    private val remoteDataSource: ConstructorColorRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: ConstructorColorLocalDataSource = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val repository = DefaultConstructorColorRepository(remoteDataSource, localDataSource, testDispatcher)

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getConstructorsColors() } returns constructorColorResponses
        coEvery { localDataSource.insertAll(any()) } returns Unit

        repository.sync()

        coEvery {
            remoteDataSource.getConstructorsColors()
            localDataSource.insertAll(constructorColorEntities)
        }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getConstructorsColors() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { localDataSource.insertAll(any()) }
    }
}
