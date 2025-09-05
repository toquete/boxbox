package com.toquete.boxbox.data.circuitimages.fake

import com.toquete.boxbox.core.database.dao.CircuitImageDao
import com.toquete.boxbox.core.database.model.CircuitImageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeCircuitImageDao : CircuitImageDao {

    private val entities: MutableStateFlow<List<CircuitImageEntity>> = MutableStateFlow(emptyList())

    override fun getCircuitImages(): Flow<List<CircuitImageEntity>> = entities

    override fun getCircuitImageById(id: String): Flow<CircuitImageEntity> {
        throw NotImplementedError("Not implemented in tests")
    }

    override suspend fun upsertAll(circuitImages: List<CircuitImageEntity>) {
        entities.update { oldValues ->
            (circuitImages + oldValues).distinctBy(CircuitImageEntity::id)
        }
    }
}
