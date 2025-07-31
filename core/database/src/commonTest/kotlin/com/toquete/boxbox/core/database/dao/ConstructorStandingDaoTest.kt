package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.constructorEntities
import com.toquete.boxbox.core.database.mock.constructorImageEntities
import com.toquete.boxbox.core.database.mock.constructorStandingEntities
import com.toquete.boxbox.core.database.mock.countryEntities
import com.toquete.boxbox.core.database.mock.fullConstructorStandingEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ConstructorStandingDaoTest : RoomDatabaseTest() {

    private lateinit var dao: ConstructorStandingDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var countryDao: CountryDao
    private lateinit var constructorImageDao: ConstructorImageDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.constructorStandingDao()
        constructorDao = db.constructorDao()
        countryDao = db.countryDao()
        constructorImageDao = db.constructorImageDao()
    }

    @AfterTest
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
