package com.toquete.boxbox.data.raceresults.repository

import com.toquete.boxbox.core.common.MAX_RESPONSE_LIMIT
import com.toquete.boxbox.core.database.dao.RaceResultDao
import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.data.raceresults.model.toDomain
import com.toquete.boxbox.data.raceresults.model.toEntity
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import com.toquete.boxbox.domain.repository.RaceResultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultRaceResultRepository @Inject constructor(
    private val remoteDataSource: RaceResultRemoteDataSource,
    private val raceResultDao: RaceResultDao
) : RaceResultRepository {

    override fun getRaceResultsBySeasonAndRound(season: String, round: Int): Flow<List<RaceResult>> {
        return raceResultDao.getRaceResultsBySeasonAndRound(season, round)
            .map { it.map(RaceResultWithDriverAndConstructorEntity::toDomain) }
    }

    override suspend fun sync() {
        var offset = 0
        do {
            val data = remoteDataSource.getRaceResults(offset)
            val racesList = data.data.raceTable.races
            raceResultDao.upsertAll(racesList.toEntity())
            offset += MAX_RESPONSE_LIMIT
        } while (offset <= data.data.totalPages)
    }
}
