package com.toquete.boxbox.data.constructorcolors.repository

import com.toquete.boxbox.core.database.dao.ConstructorColorDao
import com.toquete.boxbox.core.testing.data.constructorColorEntities
import com.toquete.boxbox.data.constructorcolors.fake.FakeConstructorColorDao
import com.toquete.boxbox.data.constructorcolors.fake.FakeConstructorColorRemoteDataSource
import com.toquete.boxbox.data.constructorcolors.source.remote.ConstructorColorRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

internal class DefaultConstructorColorRepositoryTest {

    private lateinit var remoteDataSource: ConstructorColorRemoteDataSource
    private lateinit var constructorColorDao: ConstructorColorDao
    private lateinit var repository: DefaultConstructorColorRepository

    @Before
    fun setUp() {
        remoteDataSource = FakeConstructorColorRemoteDataSource()
        constructorColorDao = FakeConstructorColorDao()
        repository = DefaultConstructorColorRepository(remoteDataSource, constructorColorDao)
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        repository.sync()

        val result = constructorColorDao.getConstructorColors().first()
        assertContentEquals(constructorColorEntities, result)
    }
}
