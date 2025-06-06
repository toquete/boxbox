package com.toquete.boxbox.data.constructorcolors.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.testing.data.constructorColorResponses
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultConstructorColorRemoteDataSourceTest {

    private val remoteDatabase: BoxBoxRemoteDatabase = mockk(relaxed = true)
    private val dataSource = DefaultConstructorColorRemoteDataSource(remoteDatabase)

    @Test
    fun `getConstructorsColors should return all constructors colors from remote when called`() = runTest {
        coEvery { remoteDatabase.getConstructorColors() } returns flowOf(constructorColorResponses)

        val result = dataSource.getConstructorsColors()

        assertContentEquals(constructorColorResponses, result)
    }
}
