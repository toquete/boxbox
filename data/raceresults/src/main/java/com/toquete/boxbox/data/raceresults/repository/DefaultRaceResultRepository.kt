package com.toquete.boxbox.data.raceresults.repository

import com.toquete.boxbox.core.common.MAX_RESPONSE_LIMIT
import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.data.raceresults.model.toDomain
import com.toquete.boxbox.data.raceresults.model.toEntity
import com.toquete.boxbox.data.raceresults.source.local.RaceResultLocalDataSource
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultRaceResultRepository @Inject constructor(
    private val remoteDataSource: RaceResultRemoteDataSource,
    private val localDataSource: RaceResultLocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineContext
) : RaceResultRepository {

    override fun getRaceResultsBySeasonAndRound(season: String, round: Int): Flow<List<RaceResult>> {
        return localDataSource.getRaceResultsBySeasonAndRound(season, round)
            .map { it.map(RaceResultWithDriverAndConstructorEntity::toDomain) }
            .flowOn(dispatcher)
    }

    override suspend fun sync() {
        withContext(dispatcher) {
            var offset = 0
            do {
                val data = remoteDataSource.getRaceResults(offset)
                val racesList = data.data.raceTable.races
                localDataSource.insertAll(racesList.toEntity())
                offset += MAX_RESPONSE_LIMIT
            } while (offset <= data.data.totalPages)
        }
    }
}
