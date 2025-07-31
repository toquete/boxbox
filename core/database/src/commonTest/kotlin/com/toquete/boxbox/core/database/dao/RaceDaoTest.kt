package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.circuitEntities
import com.toquete.boxbox.core.database.mock.circuitImageEntities
import com.toquete.boxbox.core.database.mock.countryEntities
import com.toquete.boxbox.core.database.mock.raceEntities
import com.toquete.boxbox.core.database.mock.racesWithCircuits
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class RaceDaoTest : RoomDatabaseTest() {

    private lateinit var dao: RaceDao
    private lateinit var circuitDao: CircuitDao
    private lateinit var countryDao: CountryDao
    private lateinit var circuitImageDao: CircuitImageDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.raceDao()
        circuitDao = db.circuitDao()
        countryDao = db.countryDao()
        circuitImageDao = db.circuitImageDao()
    }

    @AfterTest
    fun tearDown() {
        db.close()
    }

    @Test
    fun testGetUpcomingRacesBySeason() = runTest {
        countryDao.upsertAll(countryEntities)
        circuitDao.upsertAll(circuitEntities)
        circuitImageDao.upsertAll(circuitImageEntities)
        dao.upsertAll(raceEntities)

        val result = dao.getUpcomingRacesBySeason(season = "2023", today = "2023-03-04").first()

        assertContentEquals(racesWithCircuits, result)
    }

    @Test
    fun testGetPastRacesBySeason() = runTest {
        countryDao.upsertAll(countryEntities)
        circuitDao.upsertAll(circuitEntities)
        circuitImageDao.upsertAll(circuitImageEntities)
        dao.upsertAll(raceEntities)

        val result = dao.getPastRacesBySeason(season = "2023", today = "2023-03-06").first()

        assertContentEquals(racesWithCircuits.subList(0, 1), result)
    }
}
