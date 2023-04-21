package com.toquete.boxbox.data.fullconstructorstandings.source.local

import com.toquete.boxbox.database.model.FullConstructorStandingEntity
import kotlinx.coroutines.flow.Flow

interface FullConstructorStandingsLocalDataSource {

    fun getFullConstructorStandings(): Flow<List<FullConstructorStandingEntity>>
}