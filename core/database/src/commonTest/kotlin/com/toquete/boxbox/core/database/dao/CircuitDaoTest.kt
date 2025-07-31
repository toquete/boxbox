package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.circuitEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class CircuitDaoTest : RoomDatabaseTest() {

    private lateinit var circuitDao: CircuitDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        circuitDao = db.circuitDao()
    }

    @AfterTest
    fun tearDown() {
        db.close()
    }

    @Test
    fun testCircuitInsert() = runTest {
        circuitDao.upsertAll(circuitEntities)

        val result = circuitDao.getCircuits().first()

        assertContentEquals(circuitEntities, result)
    }
}
