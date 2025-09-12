package com.toquete.boxbox.data.countries.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.data.countries.fake.FakeBoxBoxRemoteDatabase
import com.toquete.boxbox.data.countries.mock.countryResponses
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DefaultCountryRemoteDataSourceTest {

    private lateinit var remoteDatabase: BoxBoxRemoteDatabase
    private lateinit var dataSource: DefaultCountryRemoteDataSource

    @BeforeTest
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
