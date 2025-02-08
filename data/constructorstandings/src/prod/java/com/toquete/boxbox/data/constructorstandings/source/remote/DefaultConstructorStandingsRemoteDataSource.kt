package com.toquete.boxbox.data.constructorstandings.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.ConstructorStandingResponse
import javax.inject.Inject

internal class DefaultConstructorStandingsRemoteDataSource @Inject constructor(
    private val service: BoxBoxService
) : ConstructorStandingsRemoteDataSource {

    override suspend fun getConstructorStandings(): List<ConstructorStandingResponse> {
        return service.getConstructorStandings()
            .data
            .standingTable
            .standingsLists
            .firstOrNull()
            ?.constructorStandings
            .orEmpty()
    }
}
