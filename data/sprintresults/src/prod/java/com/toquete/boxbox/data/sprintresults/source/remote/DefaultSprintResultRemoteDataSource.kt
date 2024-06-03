package com.toquete.boxbox.data.sprintresults.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.RaceResponse
import javax.inject.Inject

internal class DefaultSprintResultRemoteDataSource @Inject constructor(
    private val service: BoxBoxService
) : SprintResultRemoteDataSource {

    override suspend fun getSprintResults(): List<RaceResponse> {
        return service.getSprintRaceResults()
            .data
            .raceTable
            .races
    }
}
