package com.toquete.boxbox.data.countries.repository

import com.toquete.boxbox.core.testing.data.countryEntities
import com.toquete.boxbox.core.testing.data.countryResponses
import com.toquete.boxbox.data.countries.source.local.CountryLocalDataSource
import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultCountryRepositoryTest {

    private val remoteDataSource: CountryRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: CountryLocalDataSource = mockk(relaxed = true)
    private val repository = DefaultCountryRepository(remoteDataSource, localDataSource)

    @Test
    fun `sync should return true when remote data is saved in database`() = runTest {
        coEvery { remoteDataSource.getCountries() } returns countryResponses
        coEvery { localDataSource.insertAll(any()) } returns Unit

        val result = repository.sync()

        assertTrue(result)
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getCountries() } returns countryResponses
        coEvery { localDataSource.insertAll(any()) } returns Unit

        repository.sync()

        coVerify { localDataSource.insertAll(countryEntities) }
    }

    @Test
    fun `sync should return false when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getCountries() } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }

    @Test
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getCountries() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { localDataSource.insertAll(any()) }
    }

    @Test
    fun `sync should return false when local data insertion returns error`() = runTest {
        coEvery { remoteDataSource.getCountries() } returns countryResponses
        coEvery { localDataSource.insertAll(any()) } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }
}
