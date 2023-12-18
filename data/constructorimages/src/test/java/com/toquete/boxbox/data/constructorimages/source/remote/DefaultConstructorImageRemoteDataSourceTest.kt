package com.toquete.boxbox.data.constructorimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import com.toquete.boxbox.core.testing.data.constructorImageResponses
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultConstructorImageRemoteDataSourceTest {

    private val remoteDatabase: BoxBoxRemoteDatabase = mockk(relaxed = true)
    private val dataSource = DefaultConstructorImageRemoteDataSource(remoteDatabase)

    @Test
    fun `getConstructorsImages should return all constructors images from remote when called`() = runTest {
        coEvery {
            remoteDatabase.getCollection(id = "constructor_image", ConstructorImageResponse::class.java)
        } returns constructorImageResponses

        val result = dataSource.getConstructorsImages()

        assertContentEquals(constructorImageResponses, result)
    }
}
