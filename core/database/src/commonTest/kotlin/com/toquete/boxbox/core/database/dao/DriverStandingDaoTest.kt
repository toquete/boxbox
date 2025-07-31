package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
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
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class DriverStandingDaoTest : RoomDatabaseTest() {

    private lateinit var dao: DriverStandingDao
    private lateinit var driverDao: DriverDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var driverImageDao: DriverImageDao
    private lateinit var constructorImageDao: ConstructorImageDao
    private lateinit var countryDao: CountryDao
    private lateinit var constructorColorDao: ConstructorColorDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.driverStandingDao()
        driverDao = db.driverDao()
        constructorDao = db.constructorDao()
        driverImageDao = db.driverImageDao()
        constructorImageDao = db.constructorImageDao()
        countryDao = db.countryDao()
        constructorColorDao = db.constructorColorDao()
    }

    @AfterTest
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
