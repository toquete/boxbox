package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.testing.data.constructorStandingEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ConstructorStandingDaoTest {

    private lateinit var dao: ConstructorStandingDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.constructorStandingDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testConstructorStandingsInsert() = runTest {
        val expectedList = constructorStandingEntities.mapIndexed { index, entity ->
            entity.copy(id = index + 1)
        }

        dao.insertAll(constructorStandingEntities)

        val result = dao.getConstructorStandings().first()

        assertContentEquals(expectedList, result)
    }

    @Test
    fun testConstructorStandingsDelete() = runTest {
        dao.insertAll(constructorStandingEntities)

        dao.deleteAll()

        val result = dao.getConstructorStandings().first()

        assertEquals(emptyList(), result)
    }

    @Test
    fun testConstructorStandingsDeleteAndInsertInTransaction() = runTest {
        val newList = constructorStandingEntities.map { entity ->
            entity.copy(points = "300")
        }
        val expectedList = newList.map { entity ->
            entity.copy(id = 2)
        }
        dao.insertAll(constructorStandingEntities)

        dao.deleteAndInsertInTransaction(newList)

        val result = dao.getConstructorStandings().first()

        assertContentEquals(expectedList, result)
    }
}
