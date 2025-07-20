package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.core.testing.data.constructorImageEntities
import com.toquete.boxbox.core.testing.data.constructorStandingEntities
import com.toquete.boxbox.core.testing.data.countryEntities
import com.toquete.boxbox.core.testing.data.fullConstructorStandingEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class ConstructorStandingDaoTest {

    private lateinit var dao: ConstructorStandingDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var countryDao: CountryDao
    private lateinit var constructorImageDao: ConstructorImageDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.constructorStandingDao()
        constructorDao = db.constructorDao()
        countryDao = db.countryDao()
        constructorImageDao = db.constructorImageDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testConstructorStandingsInsert() = runTest {
        dao.insertAll(constructorStandingEntities)

        val result = dao.getConstructorStandings().first()

        assertContentEquals(constructorStandingEntities, result)
    }

    @Test
    fun testConstructorStandingsDelete() = runTest {
        dao.insertAll(constructorStandingEntities)

        dao.deleteAll()

        val result = dao.getConstructorStandings().first()

        assertEquals(emptyList(), result)
    }

    @Test
    fun testFullConstructorStandingSelect() = runTest {
        dao.deleteAndInsertAllInTransaction(constructorStandingEntities)
        constructorDao.upsertAll(constructorEntities)
        constructorImageDao.upsertAll(constructorImageEntities)
        countryDao.upsertAll(countryEntities)

        val result = dao.getFullConstructorStandings().first()

        assertContentEquals(fullConstructorStandingEntities, result)
    }
}
