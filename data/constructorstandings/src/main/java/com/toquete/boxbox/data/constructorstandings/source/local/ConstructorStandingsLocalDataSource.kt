package com.toquete.boxbox.data.constructorstandings.source.local

import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import kotlinx.coroutines.flow.Flow

interface ConstructorStandingsLocalDataSource {

    suspend fun insertAll(constructorStandings: List<ConstructorStandingEntity>)

    fun getConstructorStandings(): Flow<List<FullConstructorStandingEntity>>
}
