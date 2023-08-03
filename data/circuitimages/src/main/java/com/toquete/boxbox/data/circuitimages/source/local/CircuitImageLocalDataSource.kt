package com.toquete.boxbox.data.circuitimages.source.local

import com.toquete.boxbox.core.database.model.CircuitImageEntity

internal interface CircuitImageLocalDataSource {

    suspend fun insertAll(circuitImages: List<CircuitImageEntity>)
}
