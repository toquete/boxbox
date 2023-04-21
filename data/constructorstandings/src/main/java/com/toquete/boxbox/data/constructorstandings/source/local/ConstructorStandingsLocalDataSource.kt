package com.toquete.boxbox.data.constructorstandings.source.local

import com.toquete.boxbox.database.model.ConstructorStandingEntity

interface ConstructorStandingsLocalDataSource {

    suspend fun insertAll(constructorStandings: List<ConstructorStandingEntity>)
}