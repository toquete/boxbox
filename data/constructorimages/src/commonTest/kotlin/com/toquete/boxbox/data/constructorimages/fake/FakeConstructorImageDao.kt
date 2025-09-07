package com.toquete.boxbox.data.constructorimages.fake

import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.database.model.ConstructorImageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeConstructorImageDao : ConstructorImageDao {

    private val entities: MutableStateFlow<List<ConstructorImageEntity>> = MutableStateFlow(emptyList())

    override fun getConstructorImages(): Flow<List<ConstructorImageEntity>> = entities

    override fun getConstructorImageById(id: String): Flow<ConstructorImageEntity> {
        throw NotImplementedError("Not implemented in tests")
    }

    override suspend fun upsertAll(constructorImages: List<ConstructorImageEntity>) {
        entities.update { oldValue ->
            (constructorImages + oldValue).distinctBy(ConstructorImageEntity::id)
        }
    }
}
