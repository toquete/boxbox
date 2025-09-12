package com.toquete.boxbox.data.countries.fake

import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.database.model.CountryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeCountryDao : CountryDao {

    private val entities: MutableStateFlow<List<CountryEntity>> = MutableStateFlow(emptyList())

    override fun getCountries(): Flow<List<CountryEntity>> = entities

    override fun getCountryById(id: String): Flow<CountryEntity> {
        return entities.map { list -> list.first { it.id == id } }
    }

    override suspend fun upsertAll(countries: List<CountryEntity>) {
        entities.update { oldValue ->
            (oldValue + countries).distinctBy(CountryEntity::id)
        }
    }
}
