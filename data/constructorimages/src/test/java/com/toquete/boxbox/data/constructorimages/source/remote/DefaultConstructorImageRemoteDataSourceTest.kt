package com.toquete.boxbox.data.constructorimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.testing.data.constructorImageResponses
import com.toquete.boxbox.data.constructorimages.fake.FakeBoxBoxRemoteDatabase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultConstructorImageRemoteDataSourceTest {

    private lateinit var remoteDatabase: BoxBoxRemoteDatabase
    private lateinit var dataSource: DefaultConstructorImageRemoteDataSource

    @Before
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
