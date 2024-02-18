package com.toquete.boxbox.data.raceresults.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.RaceResponse
import javax.inject.Inject

internal class DefaultRaceResultRemoteDataSource @Inject constructor(
    private val service: BoxBoxService
) : RaceResultRemoteDataSource {

    override suspend fun getRaceResults(): List<RaceResponse> {
        return service.getRaceResults()
            .data
            .raceTable
            .races
    }
}
