package com.toquete.boxbox.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.toquete.boxbox.database.model.FullConstructorStandingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FullConstructorStandingDao {

    @Query(
        "SELECT standings.position, " +
        "       standings.points, " +
        "       constructors.id AS constructorId, " +
        "       constructors.name AS constructorName, " +
        "       ci.image_url AS imageUrl " +
        "  FROM constructor_standings AS standings, " +
        "       constructors, " +
        "       constructors_images AS ci " +
        " WHERE standings.id = constructors.id " +
        "   AND ci.id = constructors.id"
    )
    fun getFullConstructorStandings(): Flow<List<FullConstructorStandingEntity>>
}