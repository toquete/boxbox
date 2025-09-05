package com.toquete.boxbox.data.races.source.remote

import com.toquete.boxbox.core.network.model.RaceResponse

internal fun interface RaceRemoteDataSource {

    suspend fun getRaces(): List<RaceResponse>
}
