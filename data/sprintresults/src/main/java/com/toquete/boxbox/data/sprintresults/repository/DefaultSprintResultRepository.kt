package com.toquete.boxbox.data.sprintresults.repository

import com.toquete.boxbox.core.common.MAX_RESPONSE_LIMIT
import com.toquete.boxbox.core.database.dao.SprintRaceResultDao
import com.toquete.boxbox.core.database.model.SprintRaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.data.sprintresults.model.toDomain
import com.toquete.boxbox.data.sprintresults.model.toEntity
import com.toquete.boxbox.data.sprintresults.source.remote.SprintResultRemoteDataSource
import com.toquete.boxbox.domain.repository.SprintResultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DefaultSprintResultRepository(
    private val remoteDataSource: SprintResultRemoteDataSource,
    private val sprintRaceResultDao: SprintRaceResultDao
) : SprintResultRepository {

    override fun getSprintResultsBySeasonAndRound(season: String, round: Int): Flow<List<RaceResult>> {
        return sprintRaceResultDao.getSprintRaceResultsBySeasonAndRound(season, round)
            .map { it.map(SprintRaceResultWithDriverAndConstructorEntity::toDomain) }
    }

    override suspend fun sync() {
        var offset = 0
        do {
            val data = remoteDataSource.getSprintResults(offset)
            val list = data.data.raceTable.races
            sprintRaceResultDao.upsertAll(list.toEntity())
            offset += MAX_RESPONSE_LIMIT
        } while (offset <= data.data.totalPages)
    }
}
