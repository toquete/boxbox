package com.toquete.boxbox.data.countries.repository

import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.data.countries.fake.FakeCountryDao
import com.toquete.boxbox.data.countries.fake.FakeCountryRemoteDataSource
import com.toquete.boxbox.data.countries.mock.countryEntities
import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DefaultCountryRepositoryTest {

    private lateinit var remoteDataSource: CountryRemoteDataSource
    private lateinit var countryDao: CountryDao
    private lateinit var repository: DefaultCountryRepository

    @BeforeTest
    fun setUp() {
        remoteDataSource = FakeCountryRemoteDataSource()
        countryDao = FakeCountryDao()
        repository = DefaultCountryRepository(remoteDataSource, countryDao)
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        repository.sync()

        val result = countryDao.getCountries()
        assertContentEquals(countryEntities, result.first())
    }
}
