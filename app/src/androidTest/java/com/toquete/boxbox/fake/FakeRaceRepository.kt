package com.toquete.boxbox.fake

import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.core.testing.data.races
import com.toquete.boxbox.domain.repository.RaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRaceRepository : RaceRepository {
    override fun getUpcomingRacesBySeason(
        season: String,
        today: String
    ): Flow<List<Race>> {
        return flowOf(races)
    }

    override fun getPastRacesBySeason(
        season: String,
        today: String
    ): Flow<List<Race>> {
        return getUpcomingRacesBySeason(season, today)
    }

    override suspend fun sync() = Unit
}
