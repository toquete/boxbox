package com.toquete.boxbox.data.countries.fake

import com.toquete.boxbox.core.network.model.CountryResponse
import com.toquete.boxbox.data.countries.mock.countryResponses
import com.toquete.boxbox.data.countries.source.remote.CountryRemoteDataSource

class FakeCountryRemoteDataSource : CountryRemoteDataSource {
    override suspend fun getCountries(): List<CountryResponse> = countryResponses
}
