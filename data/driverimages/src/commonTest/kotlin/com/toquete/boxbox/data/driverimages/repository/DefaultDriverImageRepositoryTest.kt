package com.toquete.boxbox.data.driverimages.repository

import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.data.driverimages.fake.FakeDriverImageDao
import com.toquete.boxbox.data.driverimages.fake.FakeDriverImageRemoteDataSource
import com.toquete.boxbox.data.driverimages.mock.driverImageEntities
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DefaultDriverImageRepositoryTest {

    private lateinit var remoteDataSource: DriverImageRemoteDataSource
    private lateinit var driverImageDao: DriverImageDao
    private lateinit var repository: DefaultDriverImageRepository

    @BeforeTest
    fun setup() {
        remoteDataSource = FakeDriverImageRemoteDataSource()
        driverImageDao = FakeDriverImageDao()
        repository = DefaultDriverImageRepository(remoteDataSource, driverImageDao)
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        repository.sync()

        val result = driverImageDao.getDriverImages()
        assertContentEquals(driverImageEntities, result.first())
    }
}
