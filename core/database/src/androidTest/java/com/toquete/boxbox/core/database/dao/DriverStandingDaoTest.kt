package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.testing.data.constructorColorEntities
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
        dao.upsertAll(driverStandingEntities)

        val result = dao.getDriverStandings().first()

        assertContentEquals(driverStandingEntities, result)
    }

    @Test
    fun testDriverStandingsDelete() = runTest {
        dao.upsertAll(driverStandingEntities)

        dao.deleteAll()

        val result = dao.getDriverStandings().first()

        assertEquals(emptyList(), result)
    }

    @Test
    fun testFullDriverStandingSelect() = runTest {
        dao.upsertAll(driverStandingEntities)
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
