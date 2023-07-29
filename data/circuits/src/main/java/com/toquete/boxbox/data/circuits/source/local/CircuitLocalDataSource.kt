package com.toquete.boxbox.data.circuits.source.local

import com.toquete.boxbox.core.database.model.CircuitEntity

interface CircuitLocalDataSource {

    suspend fun insertAll(circuits: List<CircuitEntity>)
}
