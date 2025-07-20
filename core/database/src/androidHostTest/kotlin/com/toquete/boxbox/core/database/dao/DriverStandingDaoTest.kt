package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.mock.constructorColorEntities
import com.toquete.boxbox.core.database.mock.constructorEntities
import com.toquete.boxbox.core.database.mock.constructorImageEntities
import com.toquete.boxbox.core.database.mock.countryEntities
import com.toquete.boxbox.core.database.mock.driverEntities
import com.toquete.boxbox.core.database.mock.driverImageEntities
import com.toquete.boxbox.core.database.mock.driverStandingEntities
import com.toquete.boxbox.core.database.mock.fullDriverStandingEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class DriverStandingDaoTest {

    private lateinit var dao: DriverStandingDao
    private lateinit var driverDao: DriverDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var driverImageDao: DriverImageDao
    private lateinit var constructorImageDao: ConstructorImageDao
    private lateinit var countryDao: CountryDao
    private lateinit var constructorColorDao: ConstructorColorDao
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
        constructorColorDao = db.constructorColorDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testDriverStandingsInsert() = runTest {
        dao.insertAll(driverStandingEntities)

        val result = dao.getDriverStandings().first()

        assertContentEquals(driverStandingEntities, result)
    }

    @Test
    fun testDriverStandingsDelete() = runTest {
        dao.insertAll(driverStandingEntities)

        dao.deleteAll()

        val result = dao.getDriverStandings().first()

        assertEquals(emptyList(), result)
    }

    @Test
    fun testFullDriverStandingSelect() = runTest {
        dao.deleteAndInsertAllInTransaction(driverStandingEntities)
        driverDao.upsertAll(driverEntities)
        constructorDao.upsertAll(constructorEntities)
        driverImageDao.upsertAll(driverImageEntities)
        constructorImageDao.upsertAll(constructorImageEntities)
        countryDao.upsertAll(countryEntities)
        constructorColorDao.upsertAll(constructorColorEntities)

        val result = dao.getFullDriverStandings().first()

        assertContentEquals(fullDriverStandingEntities, result)
    }
}
