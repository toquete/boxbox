package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.RaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RaceDao {

    /**
     * Get races by season from database. The season param must be a string
     * containing the year of the season, e.g. "2023".
     *
     * @param season the year of the season
     */
    @Query("SELECT * FROM races WHERE season = :season")
    fun getRacesBySeason(season: String): Flow<List<RaceEntity>>

    @Upsert
    suspend fun upsertAll(races: List<RaceEntity>)
}
