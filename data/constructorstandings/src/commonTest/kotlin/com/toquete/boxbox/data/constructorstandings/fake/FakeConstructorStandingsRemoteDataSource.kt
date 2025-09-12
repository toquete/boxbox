package com.toquete.boxbox.data.constructorstandings.fake

import com.toquete.boxbox.core.network.model.ConstructorStandingResponse
import com.toquete.boxbox.data.constructorstandings.mock.constructorStandingsResponse
import com.toquete.boxbox.data.constructorstandings.source.remote.ConstructorStandingsRemoteDataSource

class FakeConstructorStandingsRemoteDataSource : ConstructorStandingsRemoteDataSource {
    override suspend fun getConstructorStandings(): List<ConstructorStandingResponse> = constructorStandingsResponse
}
