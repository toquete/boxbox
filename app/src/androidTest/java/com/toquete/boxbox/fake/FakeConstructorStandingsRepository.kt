package com.toquete.boxbox.fake

import com.toquete.boxbox.core.model.ConstructorStanding
import com.toquete.boxbox.core.testing.data.constructorStandings
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeConstructorStandingsRepository : ConstructorStandingsRepository {
    override fun getConstructorStandings(): Flow<List<ConstructorStanding>> {
        return flowOf(constructorStandings)
    }

    override suspend fun sync() = Unit
}
