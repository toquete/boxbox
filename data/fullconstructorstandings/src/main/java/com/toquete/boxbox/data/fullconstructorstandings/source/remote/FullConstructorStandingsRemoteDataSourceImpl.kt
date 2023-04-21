package com.toquete.boxbox.data.fullconstructorstandings.source.remote

import com.toquete.boxbox.network.BoxBoxService
import com.toquete.boxbox.network.model.ConstructorStandingResponse
import javax.inject.Inject

internal class FullConstructorStandingsRemoteDataSourceImpl @Inject constructor(
    private val service: BoxBoxService
) : FullConstructorStandingsRemoteDataSource {

    override suspend fun getConstructorStandings(): List<ConstructorStandingResponse> {
        return service.getConstructorStandings()
            .data
            .standingTable
            .standingsLists
            .first()
            .constructorStandings
    }
}