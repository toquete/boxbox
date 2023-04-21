package com.toquete.boxbox.data.fullconstructorstandings.repository

import com.toquete.boxbox.model.FullConstructorStanding
import kotlinx.coroutines.flow.Flow

interface FullConstructorStandingsRepository {

    fun getFullConstructorStandings(): Flow<List<FullConstructorStanding>>
}