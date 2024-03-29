package com.toquete.boxbox.data.races.source.local

import com.toquete.boxbox.core.database.dao.RaceDao
import com.toquete.boxbox.core.database.model.RaceEntity
import com.toquete.boxbox.core.database.model.RaceWithCircuitEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultRaceLocalDataSource @Inject constructor(
    private val dao: RaceDao
) : RaceLocalDataSource {

    override fun getRacesBySeason(season: String): Flow<List<RaceWithCircuitEntity>> {
        return dao.getRacesBySeason(season)
    }

    override suspend fun insertAll(races: List<RaceEntity>) {
        dao.upsertAll(races)
    }
}
