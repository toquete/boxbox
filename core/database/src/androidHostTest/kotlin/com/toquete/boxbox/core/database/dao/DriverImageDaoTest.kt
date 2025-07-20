package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.mock.driverImageEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class DriverImageDaoTest {

    private lateinit var dao: DriverImageDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.driverImageDao()
    }

    @After
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
