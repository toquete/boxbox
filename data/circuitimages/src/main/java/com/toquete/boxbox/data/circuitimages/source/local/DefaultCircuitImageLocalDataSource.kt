package com.toquete.boxbox.data.circuitimages.source.local

import com.toquete.boxbox.core.database.dao.CircuitImageDao
import com.toquete.boxbox.core.database.model.CircuitImageEntity
import javax.inject.Inject

internal class DefaultCircuitImageLocalDataSource @Inject constructor(
    private val dao: CircuitImageDao
) : CircuitImageLocalDataSource {

    override suspend fun insertAll(circuitImages: List<CircuitImageEntity>) {
        dao.upsertAll(circuitImages)
    }
}
