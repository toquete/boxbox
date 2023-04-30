package com.toquete.boxbox.data.fullconstructorstandings.source.remote

import com.toquete.boxbox.core.network.model.ConstructorStandingResponse

interface FullConstructorStandingsRemoteDataSource {

    suspend fun getConstructorStandings(): List<ConstructorStandingResponse>
}