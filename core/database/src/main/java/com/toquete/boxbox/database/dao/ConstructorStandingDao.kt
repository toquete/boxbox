package com.toquete.boxbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.toquete.boxbox.database.model.ConstructorStandingEntity

@Dao
interface ConstructorStandingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(constructorStandings: List<ConstructorStandingEntity>)
}