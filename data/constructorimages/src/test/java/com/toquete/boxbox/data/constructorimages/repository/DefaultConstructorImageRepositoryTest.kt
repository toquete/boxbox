package com.toquete.boxbox.data.constructorimages.repository

import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.testing.data.constructorImageEntities
import com.toquete.boxbox.core.testing.data.constructorImageResponses
import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class DefaultConstructorImageRepositoryTest {

    private val remoteDataSource: ConstructorImageRemoteDataSource = mockk(relaxed = true)
    private val constructorImageDao: ConstructorImageDao = mockk(relaxed = true)
    private val repository = DefaultConstructorImageRepository(remoteDataSource, constructorImageDao)

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getConstructorsImages() } returns constructorImageResponses
        coEvery { constructorImageDao.upsertAll(any()) } returns Unit

        repository.sync()

        coVerify {
            remoteDataSource.getConstructorsImages()
            constructorImageDao.upsertAll(constructorImageEntities)
        }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getConstructorsImages() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { constructorImageDao.upsertAll(any()) }
    }
}
