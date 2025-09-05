package com.toquete.boxbox.data.circuitimages.fake

import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.data.circuitimages.mock.circuitImageResponses
import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource

class FakeCircuitImageRemoteDataSource : CircuitImageRemoteDataSource {
    override suspend fun getCircuitImages(): List<CircuitImageResponse> = circuitImageResponses
}
