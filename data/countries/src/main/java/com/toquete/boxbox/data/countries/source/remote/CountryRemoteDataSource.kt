package com.toquete.boxbox.data.countries.source.remote

import com.toquete.boxbox.core.network.model.CountryResponse

internal interface CountryRemoteDataSource {

    suspend fun getCountries(): List<CountryResponse>
}
