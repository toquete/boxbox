package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.ConstructorImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConstructorImageDao {

    @Query("SELECT * FROM constructors_images")
    fun getConstructorImages(): Flow<List<ConstructorImageEntity>>

    @Query("SELECT * FROM constructors_images WHERE id =:id")
    fun getConstructorImageById(id: String): Flow<ConstructorImageEntity>

    @Upsert
    suspend fun upsertAll(constructorImages: List<ConstructorImageEntity>)
}
