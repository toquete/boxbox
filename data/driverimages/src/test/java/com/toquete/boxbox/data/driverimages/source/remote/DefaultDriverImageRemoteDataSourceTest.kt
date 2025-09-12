package com.toquete.boxbox.data.driverimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.data.driverimages.fake.FakeBoxBoxRemoteDatabase
import com.toquete.boxbox.data.driverimages.mock.driverImageResponses
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultDriverImageRemoteDataSourceTest {

    private lateinit var remoteDatabase: BoxBoxRemoteDatabase
    private lateinit var dataSource: DefaultDriverImageRemoteDataSource

    @Before
    fun setUp() {
        remoteDatabase = FakeBoxBoxRemoteDatabase()
        dataSource = DefaultDriverImageRemoteDataSource(remoteDatabase)
    }

    @Test
    fun `getDriversImages should return all drivers images from remote when called`() = runTest {
        val result = dataSource.getDriversImages()

        assertContentEquals(driverImageResponses, result)
    }
}
