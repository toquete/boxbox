package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.RaceResultEntity
import com.toquete.boxbox.core.database.model.RaceResultWithCircuitAndDriverEntity
import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RaceResultDao {

    @Transaction
    @Query(
        "SELECT * FROM race_results " +
            "WHERE season = :season " +
            "AND round = :round " +
            "ORDER BY position ASC"
    )
    fun getRaceResultsBySeasonAndRound(season: String, round: Int): Flow<List<RaceResultWithDriverAndConstructorEntity>>

    @Query(
        "SELECT results.*, circuits.country, circuits.circuitName, drivers.code, images.image_url " +
            "FROM race_results AS results " +
            "INNER JOIN races " +
            "ON races.season = results.season " +
            "AND races.round = results.round " +
            "INNER JOIN drivers " +
            "ON drivers.id = results.driver_id " +
            "INNER JOIN circuits " +
            "ON circuits.id = races.circuit_id " +
            "LEFT JOIN drivers_images AS images " +
            "ON images.id = results.driver_id " +
            "WHERE results.season = :season " +
            "AND results.position BETWEEN 1 AND 3"
    )
    fun getRaceResultsPodiumsBySeason(season: String): Flow<List<RaceResultWithCircuitAndDriverEntity>>

    @Upsert
    suspend fun upsertAll(raceResults: List<RaceResultEntity>)
}
