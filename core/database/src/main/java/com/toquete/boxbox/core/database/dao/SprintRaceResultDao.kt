package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.SprintRaceResultEntity
import com.toquete.boxbox.core.database.model.SprintRaceResultWithDriverAndConstructorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SprintRaceResultDao {

    @Transaction
    @Query(
        "SELECT * FROM sprint_race_results " +
            "WHERE season = :season " +
            "AND round = :round " +
            "ORDER BY position ASC"
    )
    fun getSprintRaceResultsBySeasonAndRound(
        season: String,
        round: Int
    ): Flow<List<SprintRaceResultWithDriverAndConstructorEntity>>

    @Upsert
    suspend fun upsertAll(sprintRaceResults: List<SprintRaceResultEntity>)
}
