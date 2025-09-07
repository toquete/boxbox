package com.toquete.boxbox.data.constructorimages.repository

import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.data.constructorimages.fake.FakeConstructorImageDao
import com.toquete.boxbox.data.constructorimages.fake.FakeConstructorImageRemoteDataSource
import com.toquete.boxbox.data.constructorimages.mock.constructorImageEntities
import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultConstructorImageRepositoryTest {

    private lateinit var remoteDataSource: ConstructorImageRemoteDataSource
    private lateinit var constructorImageDao: ConstructorImageDao
    private lateinit var repository: DefaultConstructorImageRepository

    @Before
    fun setup() {
        remoteDataSource = FakeConstructorImageRemoteDataSource()
        constructorImageDao = FakeConstructorImageDao()
        repository = DefaultConstructorImageRepository(remoteDataSource, constructorImageDao)
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        repository.sync()

        val result = constructorImageDao.getConstructorImages().first()
        assertContentEquals(constructorImageEntities, result)
    }
}
