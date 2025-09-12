package com.toquete.boxbox.data.driverimages.fake

import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.core.database.model.DriverImageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeDriverImageDao : DriverImageDao {

    private val entities: MutableStateFlow<List<DriverImageEntity>> = MutableStateFlow(emptyList())

    override fun getDriverImages(): Flow<List<DriverImageEntity>> = entities

    override fun getDriverImageById(id: String): Flow<DriverImageEntity> {
        return entities.map { list -> list.first { it.id == id } }
    }

    override suspend fun upsertAll(driverImages: List<DriverImageEntity>) {
        entities.update { oldValue ->
            (oldValue + driverImages).distinctBy(DriverImageEntity::id)
        }
    }
}
