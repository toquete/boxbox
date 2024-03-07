package com.toquete.boxbox.data.raceresults.source.local

import com.toquete.boxbox.core.database.dao.RaceResultDao
import com.toquete.boxbox.core.database.model.RaceResultEntity
import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultRaceResultLocalDataSource @Inject constructor(
    private val dao: RaceResultDao
) : RaceResultLocalDataSource {

    override fun getRaceResultsBySeasonAndRound(
        season: String,
        round: Int
    ): Flow<List<RaceResultWithDriverAndConstructorEntity>> {
        return dao.getRaceResultsBySeasonAndRound(season, round)
    }

    override suspend fun insertAll(raceResults: List<RaceResultEntity>) {
        dao.upsertAll(raceResults)
    }
}
