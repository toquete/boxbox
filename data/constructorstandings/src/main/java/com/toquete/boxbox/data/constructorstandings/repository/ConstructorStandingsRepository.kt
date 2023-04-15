package com.toquete.boxbox.data.constructorstandings.repository

import com.toquete.boxbox.model.ConstructorStanding

interface ConstructorStandingsRepository {

    suspend fun getConstructorStandings(): List<ConstructorStanding>
}