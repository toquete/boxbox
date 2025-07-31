package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.driverEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DriverDaoTest : RoomDatabaseTest() {

    private lateinit var dao: DriverDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.driverDao()
    }

    @AfterTest
    fun tearDown() {
        db.close()
    }

    @Test
    fun testDriverInsert() = runTest {
        dao.upsertAll(driverEntities)

        val result = dao.getDrivers().first()

        assertContentEquals(driverEntities, result)
    }
}
