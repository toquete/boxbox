package com.toquete.boxbox.data.constructorcolors.fake

import com.toquete.boxbox.core.database.dao.ConstructorColorDao
import com.toquete.boxbox.core.database.model.ConstructorColorEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeConstructorColorDao : ConstructorColorDao {

    private val entities: MutableStateFlow<List<ConstructorColorEntity>> = MutableStateFlow(emptyList())

    override fun getConstructorColors(): Flow<List<ConstructorColorEntity>> = entities

    override fun getConstructorColorById(id: String): Flow<ConstructorColorEntity> {
        throw NotImplementedError("Not implemented in tests")
    }

    override suspend fun upsertAll(constructorColors: List<ConstructorColorEntity>) {
        entities.update { oldValue ->
            (constructorColors + oldValue).distinctBy(ConstructorColorEntity::id)
        }
    }
}
