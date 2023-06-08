package com.toquete.boxbox.data.countries.source.local

import com.toquete.boxbox.core.database.model.CountryEntity

internal interface CountryLocalDataSource {

    suspend fun insertAll(countries: List<CountryEntity>)
}
