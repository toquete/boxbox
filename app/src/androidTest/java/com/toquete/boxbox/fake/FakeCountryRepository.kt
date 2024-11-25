package com.toquete.boxbox.fake

import com.toquete.boxbox.domain.repository.CountryRepository

class FakeCountryRepository : CountryRepository {
    override suspend fun sync() = Unit
}
