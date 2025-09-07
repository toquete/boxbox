package com.toquete.boxbox.data.constructorimages.fake

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import com.toquete.boxbox.core.network.model.CountryResponse
import com.toquete.boxbox.core.network.model.DriverImageResponse
import com.toquete.boxbox.data.constructorimages.mock.constructorImageResponses

class FakeBoxBoxRemoteDatabase : BoxBoxRemoteDatabase {
    override suspend fun getCircuitImages(): List<CircuitImageResponse> {
        throw NotImplementedError("Not implemented in tests")
    }

    override suspend fun getConstructorColors(): List<ConstructorColorResponse> {
        throw NotImplementedError("Not implemented in tests")
    }

    override suspend fun getConstructorImages(): List<ConstructorImageResponse> = constructorImageResponses

    override suspend fun getCountries(): List<CountryResponse> {
        throw NotImplementedError("Not implemented in tests")
    }

    override suspend fun getDriversImages(): List<DriverImageResponse> {
        throw NotImplementedError("Not implemented in tests")
    }
}
