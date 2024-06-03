package com.toquete.boxbox.data.sprintresults.repository

import com.toquete.boxbox.core.database.model.SprintRaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.data.sprintresults.model.toDomain
import com.toquete.boxbox.data.sprintresults.model.toEntity
import com.toquete.boxbox.data.sprintresults.source.local.SprintResultLocalDataSource
import com.toquete.boxbox.data.sprintresults.source.remote.SprintResultRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultSprintResultRepository @Inject constructor(
    private val remoteDataSource: SprintResultRemoteDataSource,
    private val localDataSource: SprintResultLocalDataSource
) : SprintResultRepository {

    override fun getSprintResultsBySeasonAndRound(season: String, round: Int): Flow<List<RaceResult>> {
        return localDataSource.getSprintResultsBySeasonAndRound(season, round)
            .map { it.map(SprintRaceResultWithDriverAndConstructorEntity::toDomain) }
    }

    override suspend fun sync() {
        remoteDataSource.getSprintResults()
            .also { list ->
                localDataSource.insertAll(list.toEntity())
            }
    }
}
