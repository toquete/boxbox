package com.toquete.boxbox.data.constructorimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.data.constructorimages.fake.FakeBoxBoxRemoteDatabase
import com.toquete.boxbox.data.constructorimages.mock.constructorImageResponses
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DefaultConstructorImageRemoteDataSourceTest {

    private lateinit var remoteDatabase: BoxBoxRemoteDatabase
    private lateinit var dataSource: DefaultConstructorImageRemoteDataSource

    @BeforeTest
    fun setup() {
        remoteDatabase = FakeBoxBoxRemoteDatabase()
        dataSource = DefaultConstructorImageRemoteDataSource(remoteDatabase)
    }

    @Test
    fun `getConstructorsImages should return all constructors images from remote when called`() = runTest {
        val result = dataSource.getConstructorsImages()

        assertContentEquals(constructorImageResponses, result)
    }
}
