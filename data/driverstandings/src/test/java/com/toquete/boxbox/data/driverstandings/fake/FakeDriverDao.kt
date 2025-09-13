package com.toquete.boxbox.data.driverstandings.fake

import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.model.DriverEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeDriverDao : DriverDao {

    private val entities: MutableStateFlow<List<DriverEntity>> = MutableStateFlow(emptyList())

    override suspend fun upsertAll(drivers: List<DriverEntity>) {
        entities.update { oldValue ->
            (oldValue + drivers).distinctBy(DriverEntity::id)
        }
    }

    override fun getDrivers(): Flow<List<DriverEntity>> = entities
}
