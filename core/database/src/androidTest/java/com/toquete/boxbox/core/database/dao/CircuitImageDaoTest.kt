package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.testing.data.circuitImageEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CircuitImageDaoTest {

    private lateinit var dao: CircuitImageDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.circuitImageDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testCircuitImageInsert() = runTest {
        dao.upsertAll(circuitImageEntities)

        val result = dao.getCircuitImageById(id = "bahrain").first()

        assertEquals(circuitImageEntities.first(), result)
    }
}
