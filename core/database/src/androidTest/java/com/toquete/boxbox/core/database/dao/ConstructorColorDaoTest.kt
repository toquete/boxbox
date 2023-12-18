package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.testing.data.constructorColorEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ConstructorColorDaoTest {

    private lateinit var dao: ConstructorColorDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.constructorColorDao()
    }

    @After
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
