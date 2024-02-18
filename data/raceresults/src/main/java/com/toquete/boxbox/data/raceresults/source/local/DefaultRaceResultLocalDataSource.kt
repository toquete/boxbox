package com.toquete.boxbox.data.raceresults.source.local

import com.toquete.boxbox.core.database.dao.RaceResultDao
import com.toquete.boxbox.core.database.model.RaceResultEntity
import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import kotlinx.coroutines.flow.Flow

internal class DefaultRaceResultLocalDataSource(
    private val dao: RaceResultDao
) : RaceResultLocalDataSource {

    override fun getRaceResultsBySeason(season: String): Flow<List<RaceResultWithDriverAndConstructorEntity>> {
        return dao.getRaceResultsBySeason(season)
    }

    override suspend fun insertAll(raceResults: List<RaceResultEntity>) {
        dao.upsertAll(raceResults)
    }
}
