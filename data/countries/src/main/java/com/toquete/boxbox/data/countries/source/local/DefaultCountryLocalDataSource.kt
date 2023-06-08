package com.toquete.boxbox.data.countries.source.local

import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.database.model.CountryEntity

internal class DefaultCountryLocalDataSource(
    private val countryDao: CountryDao
) : CountryLocalDataSource {

    override suspend fun insertAll(countries: List<CountryEntity>) {
        countryDao.upsertAll(countries)
    }
}
