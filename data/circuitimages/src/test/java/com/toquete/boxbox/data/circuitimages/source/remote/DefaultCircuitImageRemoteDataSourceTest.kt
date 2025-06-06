package com.toquete.boxbox.data.circuitimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.testing.data.circuitImageResponses
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultCircuitImageRemoteDataSourceTest {

    private val remoteDatabase: BoxBoxRemoteDatabase = mockk(relaxed = true)
    private val dataSource = DefaultCircuitImageRemoteDataSource(remoteDatabase)

    @Test
    fun `getCircuitImages should return circuit images`() = runTest {
        coEvery { remoteDatabase.getCircuitImages() } returns flowOf(circuitImageResponses)

        val result = dataSource.getCircuitImages()

        assertContentEquals(circuitImageResponses, result)
    }
}
