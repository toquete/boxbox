package com.toquete.boxbox.data.countries.repository

import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.testing.data.countryEntities
import com.toquete.boxbox.core.testing.data.countryResponses
import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class DefaultCountryRepositoryTest {

    private val remoteDataSource: CountryRemoteDataSource = mockk(relaxed = true)
    private val countryDao: CountryDao = mockk(relaxed = true)
    private val repository = DefaultCountryRepository(remoteDataSource, countryDao)

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getCountries() } returns countryResponses
        coEvery { countryDao.upsertAll(any()) } returns Unit

        repository.sync()

        coVerify {
            remoteDataSource.getCountries()
            countryDao.upsertAll(countryEntities)
        }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getCountries() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { countryDao.upsertAll(any()) }
    }
}
