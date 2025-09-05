package com.toquete.boxbox.data.sprintresults.source.remote

import com.toquete.boxbox.core.network.model.RacesWrapper

internal fun interface SprintResultRemoteDataSource {

    suspend fun getSprintResults(offset: Int): RacesWrapper
}
