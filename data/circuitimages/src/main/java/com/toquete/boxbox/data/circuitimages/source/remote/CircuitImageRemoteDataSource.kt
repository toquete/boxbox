package com.toquete.boxbox.data.circuitimages.source.remote

import com.toquete.boxbox.core.network.model.CircuitImageResponse

internal fun interface CircuitImageRemoteDataSource {

    suspend fun getCircuitImages(): List<CircuitImageResponse>
}
