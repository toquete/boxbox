package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.circuitImageEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class CircuitImageDaoTest : RoomDatabaseTest() {

    private lateinit var dao: CircuitImageDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.circuitImageDao()
    }

    @AfterTest
    fun tearDown() {
        db.close()
    }

    @Test
    fun testGetCircuitImageById() = runTest {
        dao.upsertAll(circuitImageEntities)

        val result = dao.getCircuitImageById(id = "bahrain").first()

        assertEquals(circuitImageEntities.first(), result)
    }

    @Test
    fun testCircuitImageInsert() = runTest {
        dao.upsertAll(circuitImageEntities)

        val result = dao.getCircuitImages().first()

        assertContentEquals(circuitImageEntities, result)
    }
}
