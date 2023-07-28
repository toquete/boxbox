package com.toquete.boxbox.data.races.source.local

import com.toquete.boxbox.core.database.model.RaceEntity
import kotlinx.coroutines.flow.Flow

internal interface RaceLocalDataSource {

    fun getRacesBySeason(season: String): Flow<List<RaceEntity>>

    suspend fun insertAll(races: List<RaceEntity>)
}
