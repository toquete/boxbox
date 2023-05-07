package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.testing.data.driverEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class DriverDaoTest {

    private lateinit var dao: DriverDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.driverDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testDriverInsert() = runTest {
        dao.insertAll(driverEntities)

        val result = dao.getDrivers().first()

        assertContentEquals(driverEntities, result)
    }
}
