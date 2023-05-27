package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.core.testing.data.constructorImageEntities
import com.toquete.boxbox.core.testing.data.countryEntities
import com.toquete.boxbox.core.testing.data.driverEntities
import com.toquete.boxbox.core.testing.data.driverImageEntities
import com.toquete.boxbox.core.testing.data.driverStandingEntities
import com.toquete.boxbox.core.testing.data.fullDriverStandingEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class DriverStandingDaoTest {

    private lateinit var dao: DriverStandingDao
    private lateinit var driverDao: DriverDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var driverImageDao: DriverImageDao
    private lateinit var constructorImageDao: ConstructorImageDao
    private lateinit var countryDao: CountryDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.driverStandingDao()
        driverDao = db.driverDao()
        constructorDao = db.constructorDao()
        driverImageDao = db.driverImageDao()
        constructorImageDao = db.constructorImageDao()
        countryDao = db.countryDao()
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
        val expectedList = driverStandingEntities.map { entity ->
            entity.copy(points = "300")
        }
        dao.insertAll(driverStandingEntities)

        dao.deleteAndInsertInTransaction(expectedList)

        val result = dao.getDriverStandings().first()

        assertContentEquals(expectedList, result)
    }

    @Test
    fun testFullDriverStandingSelect() = runTest {
        dao.insertAll(driverStandingEntities)
        driverDao.upsertAll(driverEntities)
        constructorDao.upsertAll(constructorEntities)
        driverImageDao.insertAll(driverImageEntities)
        constructorImageDao.upsertAll(constructorImageEntities)
        countryDao.upsertAll(countryEntities)

        val result = dao.getFullDriverStandings().first()

        assertContentEquals(fullDriverStandingEntities, result)
    }
}
