package com.toquete.boxbox.data.fullconstructorstandings.source.remote

import com.toquete.boxbox.network.model.ConstructorStandingResponse

interface FullConstructorStandingsRemoteDataSource {

    suspend fun getConstructorStandings(): List<ConstructorStandingResponse>
}