package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FullConstructorStandingDao {

    @Query(
        "SELECT standings.position, " +
            "       standings.points, " +
            "       standings.wins, " +
            "       constructors.id AS constructorId, " +
            "       constructors.name AS constructorName, " +
            "       ci.image_url AS imageUrl, " +
            "       countries.flag_url AS flagUrl " +
            "  FROM constructor_standings AS standings, " +
            "       constructors, " +
            "       constructors_images AS ci, " +
            "       countries " +
            " WHERE standings.constructor_id = constructors.id " +
            "   AND ci.id = constructors.id " +
            "   AND countries.nationality = constructors.nationality"
    )
    fun getFullConstructorStandings(): Flow<List<FullConstructorStandingEntity>>
}
