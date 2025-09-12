package com.toquete.boxbox.data.constructorstandings.source.remote

import com.toquete.boxbox.core.network.model.ConstructorStandingResponse

internal fun interface ConstructorStandingsRemoteDataSource {

    suspend fun getConstructorStandings(): List<ConstructorStandingResponse>
}
