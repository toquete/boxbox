package com.toquete.boxbox.data.constructorimages.fake

import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import com.toquete.boxbox.data.constructorimages.mock.constructorImageResponses
import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource

class FakeConstructorImageRemoteDataSource : ConstructorImageRemoteDataSource {
    override suspend fun getConstructorsImages(): List<ConstructorImageResponse> = constructorImageResponses
}
