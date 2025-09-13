package com.toquete.boxbox.data.driverstandings.fake

import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.model.ConstructorEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeConstructorDao : ConstructorDao {

    private val entities: MutableStateFlow<List<ConstructorEntity>> = MutableStateFlow(emptyList())

    override suspend fun upsertAll(constructors: List<ConstructorEntity>) {
        entities.update { oldValue ->
            (oldValue + constructors).distinctBy(ConstructorEntity::id)
        }
    }

    override fun getConstructors(): Flow<List<ConstructorEntity>> = entities
}
