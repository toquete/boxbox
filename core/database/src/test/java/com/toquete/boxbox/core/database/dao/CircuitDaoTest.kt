package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.testing.data.circuitEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertContentEquals

@RunWith(AndroidJUnit4::class)
class CircuitDaoTest {

    private lateinit var circuitDao: CircuitDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        circuitDao = db.circuitDao()
    }

    @After
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
