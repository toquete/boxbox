package com.toquete.boxbox.data.countries.repository

import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.network.model.CountryResponse
import com.toquete.boxbox.data.countries.model.toEntity
import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource
import com.toquete.boxbox.domain.repository.CountryRepository
import javax.inject.Inject

internal class DefaultCountryRepository @Inject constructor(
    private val remoteDataSource: CountryRemoteDataSource,
    private val countryDao: CountryDao
) : CountryRepository {

    override suspend fun sync() {
        val list = remoteDataSource.getCountries()
        countryDao.upsertAll(list.map(CountryResponse::toEntity))
    }
}
