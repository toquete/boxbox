package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.toquete.boxbox.core.database.model.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries WHERE id = :id")
    fun getCountryById(id: String): Flow<CountryEntity>

    @Insert
    suspend fun insertAll(countries: List<CountryEntity>)
}
