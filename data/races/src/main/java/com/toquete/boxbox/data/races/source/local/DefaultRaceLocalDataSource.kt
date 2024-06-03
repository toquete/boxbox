package com.toquete.boxbox.data.races.source.local

import com.toquete.boxbox.core.database.dao.RaceDao
import com.toquete.boxbox.core.database.model.RaceEntity
import com.toquete.boxbox.core.database.model.RaceWithCircuitEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultRaceLocalDataSource @Inject constructor(
    private val dao: RaceDao
) : RaceLocalDataSource {

    override fun getUpcomingRacesBySeason(season: String, today: String): Flow<List<RaceWithCircuitEntity>> {
        return dao.getUpcomingRacesBySeason(season, today)
    }

    override fun getPastRacesBySeason(season: String, today: String): Flow<List<RaceWithCircuitEntity>> {
        return dao.getPastRacesBySeason(season, today)
    }

    override suspend fun insertAll(races: List<RaceEntity>) {
        dao.upsertAll(races)
    }
}
