package com.toquete.boxbox.data.driverimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.data.driverimages.mock.driverImageResponses
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultDriverImageRemoteDataSourceTest {

    private val remoteDatabase: BoxBoxRemoteDatabase = mockk(relaxed = true)
    private val dataSource = DefaultDriverImageRemoteDataSource(remoteDatabase)

    @Test
    fun `getDriversImages should return all drivers images from remote when called`() = runTest {
        coEvery { remoteDatabase.getDriversImages() } returns driverImageResponses

        val result = dataSource.getDriversImages()

        assertContentEquals(driverImageResponses, result)
    }
}
