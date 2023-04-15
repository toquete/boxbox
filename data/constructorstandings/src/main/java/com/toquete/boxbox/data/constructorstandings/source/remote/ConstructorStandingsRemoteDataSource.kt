package com.toquete.boxbox.data.constructorstandings.source.remote

import com.toquete.boxbox.model.ConstructorStanding

interface ConstructorStandingsRemoteDataSource {

    suspend fun getConstructorStandings(): List<ConstructorStanding>
}