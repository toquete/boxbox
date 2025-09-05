package com.toquete.boxbox.data.circuitimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.data.circuitimages.fake.FakeBoxBoxRemoteDatabase
import com.toquete.boxbox.data.circuitimages.mock.circuitImageResponses
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DefaultCircuitImageRemoteDataSourceTest {

    private lateinit var remoteDatabase: BoxBoxRemoteDatabase
    private lateinit var dataSource: DefaultCircuitImageRemoteDataSource

    @BeforeTest
    fun setUp() {
        remoteDatabase = FakeBoxBoxRemoteDatabase()
        dataSource = DefaultCircuitImageRemoteDataSource(remoteDatabase)
    }

    @Test
    fun `getCircuitImages should return circuit images`() = runTest {
        val result = dataSource.getCircuitImages()

        assertContentEquals(circuitImageResponses, result)
    }
}
