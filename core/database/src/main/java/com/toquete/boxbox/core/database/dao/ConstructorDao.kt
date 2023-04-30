package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.toquete.boxbox.core.database.model.ConstructorEntity

@Dao
interface ConstructorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(constructors: List<ConstructorEntity>)
}