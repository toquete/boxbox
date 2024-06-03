package com.toquete.boxbox.data.races.source.local

import com.toquete.boxbox.core.database.model.RaceEntity
import com.toquete.boxbox.core.database.model.RaceWithCircuitEntity
import kotlinx.coroutines.flow.Flow

internal interface RaceLocalDataSource {

    fun getRacesBySeason(season: String): Flow<List<RaceWithCircuitEntity>>

    fun getUpcomingRacesBySeason(season: String, today: String): Flow<List<RaceWithCircuitEntity>>

    fun getPastRacesBySeason(season: String, today: String): Flow<List<RaceWithCircuitEntity>>

    suspend fun insertAll(races: List<RaceEntity>)
}
