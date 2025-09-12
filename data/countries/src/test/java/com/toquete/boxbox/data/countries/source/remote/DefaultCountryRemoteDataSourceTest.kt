package com.toquete.boxbox.data.countries.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.testing.data.countryResponses
import com.toquete.boxbox.data.countries.fake.FakeBoxBoxRemoteDatabase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultCountryRemoteDataSourceTest {

    private lateinit var remoteDatabase: BoxBoxRemoteDatabase
    private lateinit var dataSource: DefaultCountryRemoteDataSource

    @Before
    fun setUp() {
        remoteDatabase = FakeBoxBoxRemoteDatabase()
        dataSource = DefaultCountryRemoteDataSource(remoteDatabase)
    }

    @Test
    fun `getCountries should return all countries from remote when called`() = runTest {
        val result = dataSource.getCountries()

        assertContentEquals(countryResponses, result)
    }
}
