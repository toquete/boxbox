package com.toquete.boxbox.domain.repository

import com.toquete.boxbox.core.common.util.Syncable
import com.toquete.boxbox.core.model.ConstructorStanding
import kotlinx.coroutines.flow.Flow

interface ConstructorStandingsRepository : Syncable {

    fun getConstructorStandings(): Flow<List<ConstructorStanding>>
}
