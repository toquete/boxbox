package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FullDriverStandingDao {

    @Query("SELECT standings.position," +
            "      standings.points, " +
            "      standings.wins, " +
            "      drivers.id AS driverId, " +
            "      drivers.first_name AS firstName, " +
            "      drivers.last_name AS lastName, " +
            "      di.image_url AS imageUrl," +
            "      countries.flag_url AS flagUrl," +
            "      constructors.id AS constructorId, " +
            "      constructors.name AS constructorName, " +
            "      ci.image_url AS constructorImageUrl " +
            " FROM driver_standings AS standings, " +
            "      drivers," +
            "      constructors," +
            "      drivers_images AS di," +
            "      countries," +
            "      constructors_images AS ci " +
            "WHERE standings.driver_id = drivers.id " +
            "  AND standings.constructor_id = constructors.id " +
            "  AND standings.constructor_id = ci.id " +
            "  AND standings.driver_id = di.id " +
            "  AND drivers.nationality = countries.nationality")
    fun getFullDriverStandings(): Flow<List<FullDriverStandingEntity>>
}