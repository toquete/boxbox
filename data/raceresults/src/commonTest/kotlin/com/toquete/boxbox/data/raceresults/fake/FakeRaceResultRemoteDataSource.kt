package com.toquete.boxbox.data.raceresults.fake

import com.toquete.boxbox.core.network.model.RacesWrapper
import com.toquete.boxbox.data.raceresults.mock.raceResultWrapper
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource

class FakeRaceResultRemoteDataSource : RaceResultRemoteDataSource {
    override suspend fun getRaceResults(offset: Int): RacesWrapper = raceResultWrapper
}
