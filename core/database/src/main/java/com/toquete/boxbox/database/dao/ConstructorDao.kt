package com.toquete.boxbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.toquete.boxbox.database.model.ConstructorEntity

@Dao
interface ConstructorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(constructors: List<ConstructorEntity>)
}