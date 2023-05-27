package com.toquete.boxbox.data.constructorstandings.source.remote

import com.toquete.boxbox.core.network.model.ConstructorStandingResponse

interface ConstructorStandingsRemoteDataSource {

    suspend fun getConstructorStandings(): List<ConstructorStandingResponse>
}
