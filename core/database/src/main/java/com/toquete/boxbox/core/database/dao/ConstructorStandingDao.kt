package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import com.toquete.boxbox.core.database.model.NewFullConstructorStandingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConstructorStandingDao {

    @Insert
    suspend fun insertAll(constructorStandings: List<ConstructorStandingEntity>)

    @Query("DELETE FROM constructor_standings")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsertInTransaction(constructorStandings: List<ConstructorStandingEntity>) {
        deleteAll()
        insertAll(constructorStandings)
    }

    @Query("SELECT * FROM constructor_standings")
    fun getConstructorStandings(): Flow<List<ConstructorStandingEntity>>

    @Transaction
    @Query("SELECT * FROM constructor_standings")
    fun getFullConstructorStandings(): Flow<List<NewFullConstructorStandingEntity>>
}
