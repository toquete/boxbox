package com.toquete.boxbox.data.sprintresults.source.remote

import com.toquete.boxbox.core.network.model.RaceResponse

internal interface SprintResultRemoteDataSource {

    suspend fun getSprintResults(): List<RaceResponse>
}
