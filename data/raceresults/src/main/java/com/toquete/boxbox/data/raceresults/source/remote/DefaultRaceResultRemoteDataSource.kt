package com.toquete.boxbox.data.raceresults.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.RacesWrapper

internal class DefaultRaceResultRemoteDataSource(
    private val service: BoxBoxService
) : RaceResultRemoteDataSource {

    override suspend fun getRaceResults(offset: Int): RacesWrapper {
        return service.getRaceResults(offset)
    }
}
