package com.toquete.boxbox.data.countries.source.local

import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.testing.data.countryEntities
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultCountryLocalDataSourceTest {

    private val countryDao: CountryDao = mockk(relaxed = true)
    private val dataSource = DefaultCountryLocalDataSource(countryDao)

    @Test
    fun `insertAll should insert all countries when called`() = runTest {
        coEvery { countryDao.upsertAll(any()) } returns Unit

        dataSource.insertAll(countryEntities)

        coVerify { countryDao.upsertAll(countryEntities) }
    }
}
