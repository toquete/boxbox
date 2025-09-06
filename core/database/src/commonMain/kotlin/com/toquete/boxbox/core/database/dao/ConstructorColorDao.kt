package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.ConstructorColorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConstructorColorDao {

    @Query("SELECT * FROM constructors_colors")
    fun getConstructorColors(): Flow<List<ConstructorColorEntity>>

    @Query("SELECT * FROM constructors_colors WHERE id =:id")
    fun getConstructorColorById(id: String): Flow<ConstructorColorEntity>

    @Upsert
    suspend fun upsertAll(constructorColors: List<ConstructorColorEntity>)
}
