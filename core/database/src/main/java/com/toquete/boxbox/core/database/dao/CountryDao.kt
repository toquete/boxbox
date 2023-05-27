package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries WHERE id = :id")
    fun getCountryById(id: String): Flow<CountryEntity>

    @Upsert
    suspend fun upsertAll(countries: List<CountryEntity>)
}
