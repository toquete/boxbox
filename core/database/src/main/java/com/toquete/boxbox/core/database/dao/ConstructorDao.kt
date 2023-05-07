package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toquete.boxbox.core.database.model.ConstructorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConstructorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(constructors: List<ConstructorEntity>)

    @Query("SELECT * FROM constructors")
    fun getConstructors(): Flow<List<ConstructorEntity>>
}
