package com.toquete.boxbox.data.raceresults.repository

import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.data.raceresults.model.toDomain
import com.toquete.boxbox.data.raceresults.model.toEntity
import com.toquete.boxbox.data.raceresults.source.local.RaceResultLocalDataSource
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DefaultRaceResultRepository(
    private val remoteDataSource: RaceResultRemoteDataSource,
    private val localDataSource: RaceResultLocalDataSource
) : RaceResultRepository {

    override fun getRaceResultsBySeason(season: String): Flow<List<RaceResult>> {
        return localDataSource.getRaceResultsBySeason(season)
            .map { it.map(RaceResultWithDriverAndConstructorEntity::toDomain) }
    }

    override suspend fun sync() {
        remoteDataSource.getRaceResults()
            .also { list ->
                localDataSource.insertAll(list.toEntity())
            }
    }
}
