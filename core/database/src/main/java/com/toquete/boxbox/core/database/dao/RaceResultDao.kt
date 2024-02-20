package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.RaceResultEntity
import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RaceResultDao {

    @Transaction
    @Query(
        "SELECT * FROM race_results " +
            "WHERE season = :season " +
            "ORDER BY position ASC"
    )
    fun getRaceResultsBySeason(season: String): Flow<List<RaceResultWithDriverAndConstructorEntity>>

    @Upsert
    suspend fun upsertAll(raceResults: List<RaceResultEntity>)
}
