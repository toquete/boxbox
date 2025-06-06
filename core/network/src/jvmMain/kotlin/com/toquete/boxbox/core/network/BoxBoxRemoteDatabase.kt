package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import com.toquete.boxbox.core.network.model.CountryResponse
import com.toquete.boxbox.core.network.model.DriverImageResponse
import kotlinx.coroutines.flow.Flow

interface BoxBoxRemoteDatabase {

    suspend fun <T> getCollection(id: String, type: Class<T>): List<T>
    suspend fun <T> getDocument(collection: String, id: String, type: Class<T>): T?

    fun getCircuitImages(): Flow<List<CircuitImageResponse>>
    fun getConstructorColors(): Flow<List<ConstructorColorResponse>>
    fun getConstructorImages(): Flow<List<ConstructorImageResponse>>
    fun getCountries(): Flow<List<CountryResponse>>
    fun getDriversImages(): Flow<List<DriverImageResponse>>
}
