package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.ConstructorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConstructorDao {

    @Upsert
    suspend fun upsertAll(constructors: List<ConstructorEntity>)

    @Query("SELECT * FROM constructors")
    fun getConstructors(): Flow<List<ConstructorEntity>>
}
