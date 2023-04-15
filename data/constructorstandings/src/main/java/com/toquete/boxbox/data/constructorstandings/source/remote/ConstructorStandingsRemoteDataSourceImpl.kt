package com.toquete.boxbox.data.constructorstandings.source.remote

import com.toquete.boxbox.model.ConstructorStanding
import com.toquete.boxbox.network.BoxBoxService
import com.toquete.boxbox.network.model.toConstructorStanding
import javax.inject.Inject

internal class ConstructorStandingsRemoteDataSourceImpl @Inject constructor(
    private val service: BoxBoxService
) : ConstructorStandingsRemoteDataSource {

    override suspend fun getConstructorStandings(): List<ConstructorStanding> {
        return service.getConstructorStandings()
            .data
            .standingTable
            .standingsLists
            .first()
            .constructorStandings
            .map { it.toConstructorStanding() }
    }
}