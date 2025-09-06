package com.toquete.boxbox.data.constructorcolors.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.data.constructorcolors.fake.FakeBoxBoxRemoteDatabase
import com.toquete.boxbox.data.constructorcolors.mock.constructorColorResponses
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DefaultConstructorColorRemoteDataSourceTest {

    private lateinit var remoteDatabase: BoxBoxRemoteDatabase
    private lateinit var dataSource: DefaultConstructorColorRemoteDataSource

    @BeforeTest
    fun setUp() {
        remoteDatabase = FakeBoxBoxRemoteDatabase()
        dataSource = DefaultConstructorColorRemoteDataSource(remoteDatabase)
    }

    @Test
    fun `getConstructorsColors should return all constructors colors from remote when called`() = runTest {
        val result = dataSource.getConstructorsColors()

        assertContentEquals(constructorColorResponses, result)
    }
}
