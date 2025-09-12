package com.toquete.boxbox.data.constructorstandings.fake

import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.data.constructorstandings.mock.fullConstructorStandingEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class FakeConstructorStandingDao : ConstructorStandingDao {

    private val entities: MutableStateFlow<List<ConstructorStandingEntity>> = MutableStateFlow(emptyList())

    override suspend fun insertAll(constructorStandings: List<ConstructorStandingEntity>) {
        entities.update { oldValue ->
            oldValue + constructorStandings
        }
    }

    override suspend fun deleteAll() {
        entities.update { emptyList() }
    }

    override fun getConstructorStandings(): Flow<List<ConstructorStandingEntity>> {
        entities.update { it.sortedBy { standing -> standing.position } }
        return entities
    }

    override fun getFullConstructorStandings(): Flow<List<FullConstructorStandingEntity>> {
        return flowOf(fullConstructorStandingEntities)
    }
}
