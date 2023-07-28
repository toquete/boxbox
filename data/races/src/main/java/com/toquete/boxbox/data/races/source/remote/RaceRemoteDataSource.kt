package com.toquete.boxbox.data.races.source.remote

import com.toquete.boxbox.core.network.model.RaceResponse

internal interface RaceRemoteDataSource {

    suspend fun getRaces(): List<RaceResponse>
}
