package com.toquete.boxbox.data.raceresults.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.RaceResponse

class DefaultRaceResultRemoteDataSource(
    private val service: BoxBoxService
) : RaceResultRemoteDataSource {

    override suspend fun getRaceResults(): List<RaceResponse> {
        return service.getRaceResults()
            .data
            .raceTable
            .races
    }
}
