package com.toquete.boxbox.data.circuitimages.repository

import com.toquete.boxbox.core.database.dao.CircuitImageDao
import com.toquete.boxbox.core.testing.data.circuitImageEntities
import com.toquete.boxbox.data.circuitimages.fake.FakeCircuitImageDao
import com.toquete.boxbox.data.circuitimages.fake.FakeCircuitImageRemoteDataSource
import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultCircuitImageRepositoryTest {

    private lateinit var remoteDataSource: CircuitImageRemoteDataSource
    private lateinit var circuitImageDao: CircuitImageDao
    private lateinit var repository: DefaultCircuitImageRepository

    @Before
    fun setUp() {
        remoteDataSource = FakeCircuitImageRemoteDataSource()
        circuitImageDao = FakeCircuitImageDao()
        repository = DefaultCircuitImageRepository(remoteDataSource, circuitImageDao)
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        repository.sync()

        assertContentEquals(circuitImageEntities, circuitImageDao.getCircuitImages().first())
    }
}
