package com.toquete.boxbox.data.fulldriverstandings.source.local

import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import kotlinx.coroutines.flow.Flow

internal interface FullDriverStandingsLocalDataSource {

    fun getFullDriverStandings(): Flow<List<FullDriverStandingEntity>>
}
