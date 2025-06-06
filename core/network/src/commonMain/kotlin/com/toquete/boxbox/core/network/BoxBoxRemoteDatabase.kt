package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import com.toquete.boxbox.core.network.model.CountryResponse
import com.toquete.boxbox.core.network.model.DriverImageResponse

interface BoxBoxRemoteDatabase {
    suspend fun getCircuitImages(): List<CircuitImageResponse>
    suspend fun getConstructorColors(): List<ConstructorColorResponse>
    suspend fun getConstructorImages(): List<ConstructorImageResponse>
    suspend fun getCountries(): List<CountryResponse>
    suspend fun getDriversImages(): List<DriverImageResponse>
}
