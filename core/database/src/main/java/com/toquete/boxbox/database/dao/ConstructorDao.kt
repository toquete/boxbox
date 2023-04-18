package com.toquete.boxbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toquete.boxbox.database.model.ConstructorEntity

@Dao
interface ConstructorDao {

    @Query("SELECT * FROM constructors WHERE id = :id")
    suspend fun getConstructorById(id: String): ConstructorEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(constructors: List<ConstructorEntity>)
}