package com.toquete.boxbox.data.raceresults.fake

import com.toquete.boxbox.core.database.dao.RaceResultDao
import com.toquete.boxbox.core.database.model.RaceResultEntity
import com.toquete.boxbox.core.database.model.RaceResultWithCircuitAndDriverEntity
import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.data.raceresults.mock.raceResultsWithCircuitAndDriverEntity
import com.toquete.boxbox.data.raceresults.mock.raceResultsWithDriverAndConstructor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class FakeRaceResultDao : RaceResultDao {

    private val entities: MutableStateFlow<List<RaceResultEntity>> = MutableStateFlow(emptyList())

    override fun getRaceResults(): Flow<List<RaceResultEntity>> = entities

    override fun getRaceResultsBySeasonAndRound(
        season: String,
        round: Int
    ): Flow<List<RaceResultWithDriverAndConstructorEntity>> {
        return flowOf(raceResultsWithDriverAndConstructor)
    }

    override fun getRaceResultsPodiumsBySeason(season: String): Flow<List<RaceResultWithCircuitAndDriverEntity>> {
        return flowOf(raceResultsWithCircuitAndDriverEntity)
    }

    override suspend fun upsertAll(raceResults: List<RaceResultEntity>) {
        return entities.update { oldValue ->
            (oldValue + raceResults).distinctBy { "${it.season}${it.round}${it.position}" }
        }
    }
}
