package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.toquete.boxbox.core.database.model.ConstructorImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConstructorImageDao {

    @Query("SELECT * FROM constructors_images WHERE id =:id")
    fun getConstructorImageById(id: String): Flow<ConstructorImageEntity>

    @Insert
    suspend fun insertAll(constructorImages: List<ConstructorImageEntity>)
}
