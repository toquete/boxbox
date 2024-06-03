package com.toquete.boxbox.data.sprintresults.source.local

import com.toquete.boxbox.core.database.dao.SprintRaceResultDao
import com.toquete.boxbox.core.database.model.SprintRaceResultEntity
import com.toquete.boxbox.core.database.model.SprintRaceResultWithDriverAndConstructorEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultSprintResultLocalDataSource @Inject constructor(
    private val dao: SprintRaceResultDao
) : SprintResultLocalDataSource {

    override fun getSprintResultsBySeasonAndRound(
        season: String,
        round: Int
    ): Flow<List<SprintRaceResultWithDriverAndConstructorEntity>> {
        return dao.getSprintRaceResultsBySeasonAndRound(season, round)
    }

    override suspend fun insertAll(sprintRaceResults: List<SprintRaceResultEntity>) {
        dao.upsertAll(sprintRaceResults)
    }
}
