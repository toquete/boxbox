package com.toquete.boxbox.data.countries.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.CountryResponse
import javax.inject.Inject

private const val COLLECTION = "country"

internal class DefaultCountryRemoteDataSource @Inject constructor(
    private val remoteDatabase: BoxBoxRemoteDatabase
) : CountryRemoteDataSource {

    override suspend fun getCountries(): List<CountryResponse> {
        return remoteDatabase.getCollection(COLLECTION, CountryResponse::class.java)
    }
}
