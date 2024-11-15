package com.toquete.boxbox.data.raceresults.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.RacesWrapper
import javax.inject.Inject

internal class DefaultRaceResultRemoteDataSource @Inject constructor(
    private val service: BoxBoxService
) : RaceResultRemoteDataSource {

    override suspend fun getRaceResults(offset: Int): RacesWrapper {
        return service.getRaceResults(offset)
    }
}
