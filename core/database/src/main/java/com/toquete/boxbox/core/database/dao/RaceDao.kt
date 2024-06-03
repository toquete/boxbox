package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.RaceEntity
import com.toquete.boxbox.core.database.model.RaceWithCircuitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RaceDao {

    /**
     * Get upcoming races by season from database. The season param must be a string
     * containing the year of the season, e.g. "2023".
     *
     * @param season the year of the season
     * @param today the current date in the format "yyyy-MM-dd"
     */
    @Query(
        "SELECT race.*, circuit.*, country.flag_url AS flagUrl, ci.image_url AS circuitImageUrl " +
            "FROM races AS race " +
            "INNER JOIN circuits AS circuit " +
            "ON race.circuit_id = circuit.id " +
            "LEFT JOIN circuits_images AS ci " +
            "ON circuit.id = ci.id " +
            "LEFT JOIN countries AS country " +
            "ON country.name LIKE '%' || circuit.country || '%' " +
            "OR country.id = circuit.country " +
            "OR country.alternative_id = circuit.country " +
            "WHERE race.season = :season " +
            "  AND race.date >= :today "
    )
    fun getUpcomingRacesBySeason(season: String, today: String): Flow<List<RaceWithCircuitEntity>>

    /**
     * Get past races by season from database. The season param must be a string
     * containing the year of the season, e.g. "2023".
     *
     * @param season the year of the season
     * @param today the current date in the format "yyyy-MM-dd"
     */
    @Query(
        "SELECT race.*, circuit.*, country.flag_url AS flagUrl, ci.image_url AS circuitImageUrl " +
            "FROM races AS race " +
            "INNER JOIN circuits AS circuit " +
            "ON race.circuit_id = circuit.id " +
            "LEFT JOIN circuits_images AS ci " +
            "ON circuit.id = ci.id " +
            "LEFT JOIN countries AS country " +
            "ON country.name LIKE '%' || circuit.country || '%' " +
            "OR country.id = circuit.country " +
            "OR country.alternative_id = circuit.country " +
            "WHERE race.season = :season " +
            "  AND race.date < :today " +
            "ORDER BY race.date DESC"
    )
    fun getPastRacesBySeason(season: String, today: String): Flow<List<RaceWithCircuitEntity>>

    @Upsert
    suspend fun upsertAll(races: List<RaceEntity>)
}
