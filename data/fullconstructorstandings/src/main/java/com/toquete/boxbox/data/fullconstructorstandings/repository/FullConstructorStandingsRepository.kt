package com.toquete.boxbox.data.fullconstructorstandings.repository

import com.toquete.boxbox.core.common.Syncable
import com.toquete.boxbox.model.FullConstructorStanding
import kotlinx.coroutines.flow.Flow

interface FullConstructorStandingsRepository : Syncable {

    fun getFullConstructorStandings(): Flow<List<FullConstructorStanding>>
}