package com.toquete.boxbox.data.sprintresults.source.local

import com.toquete.boxbox.core.database.model.SprintRaceResultEntity
import com.toquete.boxbox.core.database.model.SprintRaceResultWithDriverAndConstructorEntity
import kotlinx.coroutines.flow.Flow

internal interface SprintResultLocalDataSource {

    fun getSprintResultsBySeasonAndRound(
        season: String,
        round: Int
    ): Flow<List<SprintRaceResultWithDriverAndConstructorEntity>>

    suspend fun insertAll(sprintRaceResults: List<SprintRaceResultEntity>)
}
