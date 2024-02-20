package com.toquete.boxbox.data.raceresults.source.local

import com.toquete.boxbox.core.database.model.RaceResultEntity
import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import kotlinx.coroutines.flow.Flow

internal interface RaceResultLocalDataSource {

    fun getRaceResultsBySeason(season: String): Flow<List<RaceResultWithDriverAndConstructorEntity>>

    suspend fun insertAll(raceResults: List<RaceResultEntity>)
}
