package com.toquete.boxbox.data.circuits.source.local

import com.toquete.boxbox.core.database.dao.CircuitDao
import com.toquete.boxbox.core.database.model.CircuitEntity
import javax.inject.Inject

internal class DefaultCircuitLocalDataSource @Inject constructor(
    private val dao: CircuitDao
) : CircuitLocalDataSource {

    override suspend fun insertAll(circuits: List<CircuitEntity>) {
        dao.upsertAll(circuits)
    }
}
