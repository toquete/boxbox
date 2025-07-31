package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.constructorEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class ConstructorDaoTest : RoomDatabaseTest() {

    private lateinit var dao: ConstructorDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.constructorDao()
    }

    @AfterTest
    fun tearDown() {
        db.close()
    }

    @Test
    fun testConstructorsInsert() = runTest {
        dao.upsertAll(constructorEntities)

        val result = dao.getConstructors().first()

        assertContentEquals(constructorEntities, result)
    }
}
