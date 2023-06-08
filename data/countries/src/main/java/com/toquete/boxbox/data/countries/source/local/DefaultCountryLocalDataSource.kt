package com.toquete.boxbox.data.countries.source.local

import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.database.model.CountryEntity
import javax.inject.Inject

internal class DefaultCountryLocalDataSource @Inject constructor(
    private val countryDao: CountryDao
) : CountryLocalDataSource {

    override suspend fun insertAll(countries: List<CountryEntity>) {
        countryDao.upsertAll(countries)
    }
}
