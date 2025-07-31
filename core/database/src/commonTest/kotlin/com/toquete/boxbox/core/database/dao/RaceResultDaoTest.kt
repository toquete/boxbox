package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.circuitEntities
import com.toquete.boxbox.core.database.mock.constructorEntities
import com.toquete.boxbox.core.database.mock.driverEntities
import com.toquete.boxbox.core.database.mock.driverImageEntities
import com.toquete.boxbox.core.database.mock.raceEntities
import com.toquete.boxbox.core.database.mock.raceResultEntities
import com.toquete.boxbox.core.database.mock.raceResultsWithCircuitAndDriverEntity
import com.toquete.boxbox.core.database.mock.raceResultsWithDriverAndConstructor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class RaceResultDaoTest : RoomDatabaseTest() {

    private lateinit var dao: RaceResultDao
    private lateinit var driverDao: DriverDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var raceDao: RaceDao
    private lateinit var circuitDao: CircuitDao
    private lateinit var driverImageDao: DriverImageDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.raceResultDao()
        driverDao = db.driverDao()
        constructorDao = db.constructorDao()
        raceDao = db.raceDao()
        circuitDao = db.circuitDao()
        driverImageDao = db.driverImageDao()
    }

    @AfterTest
    fun tearDown() {
        db.close()
    }

    @Test
    fun testRaceResultInsert() = runTest {
        driverDao.upsertAll(driverEntities)
        constructorDao.upsertAll(constructorEntities)
        dao.upsertAll(raceResultEntities)

        val result = dao.getRaceResultsBySeasonAndRound(season = "2023", round = 1).first()

        assertContentEquals(raceResultsWithDriverAndConstructor, result)
    }

    @Test
    fun testRaceResultPodiumQuery() = runTest {
        driverDao.upsertAll(driverEntities)
        driverImageDao.upsertAll(driverImageEntities)
        raceDao.upsertAll(raceEntities)
        circuitDao.upsertAll(circuitEntities)
        dao.upsertAll(raceResultEntities)

        val result = dao.getRaceResultsPodiumsBySeason(season = "2023").first()

        assertContentEquals(raceResultsWithCircuitAndDriverEntity, result)
    }
}
