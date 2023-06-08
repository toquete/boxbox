package com.toquete.boxbox.data.countries.repository

import com.toquete.boxbox.core.network.model.CountryResponse
import com.toquete.boxbox.data.countries.model.toEntity
import com.toquete.boxbox.data.countries.source.local.CountryLocalDataSource
import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource

internal class DefaultCountryRepository(
    private val remoteDataSource: CountryRemoteDataSource,
    private val localDataSource: CountryLocalDataSource
) : CountryRepository {

    override suspend fun sync(): Boolean {
        return runCatching {
            remoteDataSource.getCountries()
                .also { list ->
                    localDataSource.insertAll(list.map(CountryResponse::toEntity))
                }
        }.isSuccess
    }
}
