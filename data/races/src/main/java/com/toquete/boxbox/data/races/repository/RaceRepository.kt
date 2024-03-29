package com.toquete.boxbox.data.races.repository

import com.toquete.boxbox.core.common.util.Syncable
import com.toquete.boxbox.core.model.Race
import kotlinx.coroutines.flow.Flow

interface RaceRepository : Syncable {

    fun getRacesBySeason(season: String): Flow<List<Race>>
}
