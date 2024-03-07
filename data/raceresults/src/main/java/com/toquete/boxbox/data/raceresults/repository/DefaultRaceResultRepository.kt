package com.toquete.boxbox.data.raceresults.repository

import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.data.raceresults.model.toDomain
import com.toquete.boxbox.data.raceresults.model.toEntity
import com.toquete.boxbox.data.raceresults.source.local.RaceResultLocalDataSource
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultRaceResultRepository @Inject constructor(
    private val remoteDataSource: RaceResultRemoteDataSource,
    private val localDataSource: RaceResultLocalDataSource
) : RaceResultRepository {

    override fun getRaceResultsBySeasonAndRound(season: String, round: Int): Flow<List<RaceResult>> {
        return localDataSource.getRaceResultsBySeasonAndRound(season, round)
            .map { it.map(RaceResultWithDriverAndConstructorEntity::toDomain) }
    }

    override suspend fun sync() {
        remoteDataSource.getRaceResults()
            .also { list ->
                localDataSource.insertAll(list.toEntity())
            }
    }
}
