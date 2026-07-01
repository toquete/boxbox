package com.toquete.boxbox.data.races.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.RaceResponse

internal class DefaultRaceRemoteDataSource(
    private val service: BoxBoxService
) : RaceRemoteDataSource {

    override suspend fun getRaces(): List<RaceResponse> {
        return service.getRaces()
            .data
            .raceTable
            .races
    }
}
