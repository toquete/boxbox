package com.toquete.boxbox.data.constructorstandings.repository

import com.toquete.boxbox.core.common.Syncable
import com.toquete.boxbox.core.model.FullConstructorStanding
import kotlinx.coroutines.flow.Flow

interface ConstructorStandingsRepository : Syncable {

    fun getFullConstructorStandings(): Flow<List<FullConstructorStanding>>
}
