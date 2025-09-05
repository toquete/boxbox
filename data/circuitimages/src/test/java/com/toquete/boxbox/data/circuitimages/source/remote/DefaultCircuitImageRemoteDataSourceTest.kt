package com.toquete.boxbox.data.circuitimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import com.toquete.boxbox.core.network.model.CountryResponse
import com.toquete.boxbox.core.network.model.DriverImageResponse
import com.toquete.boxbox.data.circuitimages.mock.circuitImageResponses
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultCircuitImageRemoteDataSourceTest {

    private val remoteDatabase = object : BoxBoxRemoteDatabase {
        override suspend fun getCircuitImages(): List<CircuitImageResponse> {
            return circuitImageResponses
        }

        override suspend fun getConstructorColors(): List<ConstructorColorResponse> = emptyList()

        override suspend fun getConstructorImages(): List<ConstructorImageResponse> = emptyList()

        override suspend fun getCountries(): List<CountryResponse> = emptyList()

        override suspend fun getDriversImages(): List<DriverImageResponse> = emptyList()
    }
    private val dataSource = DefaultCircuitImageRemoteDataSource(remoteDatabase)

    @Test
    fun `getCircuitImages should return circuit images`() = runTest {
        val result = dataSource.getCircuitImages()

        assertContentEquals(circuitImageResponses, result)
    }
}
