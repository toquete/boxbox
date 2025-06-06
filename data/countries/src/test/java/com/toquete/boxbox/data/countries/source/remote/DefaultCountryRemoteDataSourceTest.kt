package com.toquete.boxbox.data.countries.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.testing.data.countryResponses
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultCountryRemoteDataSourceTest {

    private val remoteDatabase: BoxBoxRemoteDatabase = mockk(relaxed = true)
    private val dataSource = DefaultCountryRemoteDataSource(remoteDatabase)

    @Test
    fun `getCountries should return all countries from remote when called`() = runTest {
        coEvery { remoteDatabase.getCountries() } returns countryResponses

        val result = dataSource.getCountries()

        assertContentEquals(countryResponses, result)
    }
}
