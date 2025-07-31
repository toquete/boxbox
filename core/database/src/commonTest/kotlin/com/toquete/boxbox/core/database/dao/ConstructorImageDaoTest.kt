package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.constructorImageEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstructorImageDaoTest : RoomDatabaseTest() {

    private lateinit var dao: ConstructorImageDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.constructorImageDao()
    }

    @AfterTest
    fun tearDown() {
        db.close()
    }

    @Test
    fun testConstructorImageInsert() = runTest {
        dao.upsertAll(constructorImageEntities)

        val result = dao.getConstructorImageById(id = "red_bull").first()

        assertEquals(constructorImageEntities.first(), result)
    }
}
