package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import kotlinx.coroutines.flow.Flow

interface BoxBoxRemoteDatabase {

    suspend fun <T> getCollection(id: String, type: Class<T>): List<T>
    suspend fun <T> getDocument(collection: String, id: String, type: Class<T>): T?

    fun getCircuitImages(): Flow<List<CircuitImageResponse>>
    fun getConstructorColors(): Flow<List<ConstructorColorResponse>>
}
