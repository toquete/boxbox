package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.constructorColorEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstructorColorDaoTest : RoomDatabaseTest() {

    private lateinit var dao: ConstructorColorDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.constructorColorDao()
    }

    @AfterTest
    fun tearDown() {
        db.close()
    }

    @Test
    fun testConstructorColorInsert() = runTest {
        dao.upsertAll(constructorColorEntities)

        val result = dao.getConstructorColorById(id = "red_bull").first()

        assertEquals(constructorColorEntities.first(), result)
    }
}
