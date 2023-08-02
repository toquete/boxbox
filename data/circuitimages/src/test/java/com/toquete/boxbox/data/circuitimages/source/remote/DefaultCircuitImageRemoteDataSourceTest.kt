package com.toquete.boxbox.data.circuitimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.core.testing.data.circuitImageResponses
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultCircuitImageRemoteDataSourceTest {

    private val remoteDatabase: BoxBoxRemoteDatabase = mockk(relaxed = true)
    private val dataSource = DefaultCircuitImageRemoteDataSource(remoteDatabase)

    @Test
    fun `getCircuitImages should return circuit images`() = runTest {
        coEvery {
            remoteDatabase.getCollection(
                id = "circuit_image",
                CircuitImageResponse::class.java
            )
        } returns circuitImageResponses

        val result = dataSource.getCircuitImages()

        assertContentEquals(circuitImageResponses, result)
    }
}
