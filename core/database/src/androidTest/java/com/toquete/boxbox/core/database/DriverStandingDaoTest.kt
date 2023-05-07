package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.testing.data.driverStandingEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class DriverStandingDaoTest {

    private lateinit var dao: DriverStandingDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.driverStandingDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testDriverStandingsInsert() = runTest {
        val expectedList = driverStandingEntities.mapIndexed { index, entity ->
            entity.copy(id = index + 1)
        }

        dao.insertAll(driverStandingEntities)

        val result = dao.getDriverStandings().first()

        assertContentEquals(expectedList, result)
    }

    @Test
    fun testDriverStandingsDelete() = runTest {
        dao.insertAll(driverStandingEntities)

        dao.deleteAll()

        val result = dao.getDriverStandings().first()

        assertEquals(emptyList(), result)
    }

    @Test
    fun testDriverStandingsDeleteAndInsertInTransaction() = runTest {
        val newList = driverStandingEntities.map { entity ->
            entity.copy(points = "300")
        }
        val expectedList = newList.map { entity ->
            entity.copy(id = 2)
        }
        dao.insertAll(driverStandingEntities)

        dao.deleteAndInsertInTransaction(newList)

        val result = dao.getDriverStandings().first()

        assertContentEquals(expectedList, result)
    }
}
