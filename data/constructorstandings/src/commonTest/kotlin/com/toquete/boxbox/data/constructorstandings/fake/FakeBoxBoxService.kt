package com.toquete.boxbox.data.constructorstandings.fake

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.RacesWrapper

class FakeBoxBoxService(
    private val constructorStandings: ConstructorStandingsWrapper
) : BoxBoxService {
    override suspend fun getDriverStandings(): DriverStandingsWrapper {
        throw NotImplementedError("Not implemented in tests")
    }

    override suspend fun getConstructorStandings(): ConstructorStandingsWrapper = constructorStandings

    override suspend fun getRaces(): RacesWrapper {
        throw NotImplementedError("Not implemented in tests")
    }

    override suspend fun getRaceResults(offset: Int, limit: Int): RacesWrapper {
        throw NotImplementedError("Not implemented in tests")
    }

    override suspend fun getSprintRaceResults(offset: Int, limit: Int): RacesWrapper {
        throw NotImplementedError("Not implemented in tests")
    }
}
