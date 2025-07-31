package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.driverImageEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DriverImageDaoTest : RoomDatabaseTest() {

    private lateinit var dao: DriverImageDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.driverImageDao()
    }

    @AfterTest
    fun tearDown() {
        db.close()
    }

    @Test
    fun testDriverImageInsert() = runTest {
        dao.upsertAll(driverImageEntities)

        val result = dao.getDriverImageById(id = "max_verstappen").first()

        assertEquals(driverImageEntities.first(), result)
    }
}
