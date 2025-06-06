package com.toquete.boxbox.data.circuitimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.CircuitImageResponse
import kotlinx.coroutines.flow.single

internal class DefaultCircuitImageRemoteDataSource(
    private val remoteDatabase: BoxBoxRemoteDatabase
) : CircuitImageRemoteDataSource {

    override suspend fun getCircuitImages(): List<CircuitImageResponse> {
        return remoteDatabase.getCircuitImages().single()
    }
}
