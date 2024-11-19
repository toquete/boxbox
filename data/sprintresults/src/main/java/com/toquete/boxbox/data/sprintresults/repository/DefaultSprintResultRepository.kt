package com.toquete.boxbox.data.sprintresults.repository

import com.toquete.boxbox.core.common.MAX_RESPONSE_LIMIT
import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.database.dao.SprintRaceResultDao
import com.toquete.boxbox.core.database.model.SprintRaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.data.sprintresults.model.toDomain
import com.toquete.boxbox.data.sprintresults.model.toEntity
import com.toquete.boxbox.data.sprintresults.source.remote.SprintResultRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultSprintResultRepository @Inject constructor(
    private val remoteDataSource: SprintResultRemoteDataSource,
    private val sprintRaceResultDao: SprintRaceResultDao,
    @IoDispatcher private val dispatcher: CoroutineContext
) : SprintResultRepository {

    override fun getSprintResultsBySeasonAndRound(season: String, round: Int): Flow<List<RaceResult>> {
        return sprintRaceResultDao.getSprintRaceResultsBySeasonAndRound(season, round)
            .map { it.map(SprintRaceResultWithDriverAndConstructorEntity::toDomain) }
            .flowOn(dispatcher)
    }

    override suspend fun sync() {
        withContext(dispatcher) {
            var offset = 0
            do {
                val data = remoteDataSource.getSprintResults(offset)
                val list = data.data.raceTable.races
                sprintRaceResultDao.upsertAll(list.toEntity())
                offset += MAX_RESPONSE_LIMIT
            } while (offset <= data.data.totalPages)
        }
    }
}
