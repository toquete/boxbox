package com.toquete.boxbox.data.constructorstandings.source.local

import com.toquete.boxbox.core.database.model.ConstructorStandingEntity

interface ConstructorStandingsLocalDataSource {

    suspend fun insertAll(constructorStandings: List<ConstructorStandingEntity>)
}
