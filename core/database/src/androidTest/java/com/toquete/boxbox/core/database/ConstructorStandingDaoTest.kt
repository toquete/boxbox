package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.dao.CountryDao
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
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

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
        dao.upsertAll(constructorStandingEntities)

        val result = dao.getConstructorStandings().first()

        assertContentEquals(constructorStandingEntities, result)
    }

    @Test
    fun testConstructorStandingsDelete() = runTest {
        dao.upsertAll(constructorStandingEntities)

        dao.deleteAll()

        val result = dao.getConstructorStandings().first()

        assertEquals(emptyList(), result)
    }

    @Test
    fun testFullConstructorStandingSelect() = runTest {
        dao.upsertAll(constructorStandingEntities)
        constructorDao.upsertAll(constructorEntities)
        constructorImageDao.upsertAll(constructorImageEntities)
        countryDao.upsertAll(countryEntities)

        val result = dao.getFullConstructorStandings().first()

        assertContentEquals(fullConstructorStandingEntities, result)
    }
}
