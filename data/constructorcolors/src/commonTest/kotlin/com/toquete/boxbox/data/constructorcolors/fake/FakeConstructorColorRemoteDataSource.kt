package com.toquete.boxbox.data.constructorcolors.fake

import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import com.toquete.boxbox.data.constructorcolors.mock.constructorColorResponses
import com.toquete.boxbox.data.constructorcolors.source.remote.ConstructorColorRemoteDataSource

class FakeConstructorColorRemoteDataSource : ConstructorColorRemoteDataSource {
    override suspend fun getConstructorsColors(): List<ConstructorColorResponse> = constructorColorResponses
}
