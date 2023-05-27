package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConstructorStandingDao {

    @Upsert
    suspend fun upsertAll(constructorStandings: List<ConstructorStandingEntity>)

    @Query("DELETE FROM constructor_standings")
    suspend fun deleteAll()

    @Query("SELECT * FROM constructor_standings")
    fun getConstructorStandings(): Flow<List<ConstructorStandingEntity>>

    @Transaction
    @Query("SELECT * FROM constructor_standings")
    fun getFullConstructorStandings(): Flow<List<FullConstructorStandingEntity>>
}
