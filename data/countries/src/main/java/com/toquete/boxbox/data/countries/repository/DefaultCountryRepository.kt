package com.toquete.boxbox.data.countries.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.network.model.CountryResponse
import com.toquete.boxbox.data.countries.model.toEntity
import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultCountryRepository @Inject constructor(
    private val remoteDataSource: CountryRemoteDataSource,
    private val countryDao: CountryDao,
    @IoDispatcher private val dispatcher: CoroutineContext
) : CountryRepository {

    override suspend fun sync() {
        withContext(dispatcher) {
            val list = remoteDataSource.getCountries()
            countryDao.upsertAll(list.map(CountryResponse::toEntity))
        }
    }
}
