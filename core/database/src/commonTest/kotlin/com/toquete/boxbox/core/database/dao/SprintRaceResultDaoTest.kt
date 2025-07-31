package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.constructorEntities
import com.toquete.boxbox.core.database.mock.driverEntities
import com.toquete.boxbox.core.database.mock.sprintRaceResultEntities
import com.toquete.boxbox.core.database.mock.sprintRaceResultsWithDriverAndConstructor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class SprintRaceResultDaoTest : RoomDatabaseTest() {

    private lateinit var dao: SprintRaceResultDao
    private lateinit var driverDao: DriverDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var raceDao: RaceDao
    private lateinit var circuitDao: CircuitDao
    private lateinit var driverImageDao: DriverImageDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.sprintRaceResultDao()
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
    fun testSprintRaceResultInsert() = runTest {
        driverDao.upsertAll(driverEntities)
        constructorDao.upsertAll(constructorEntities)
        dao.upsertAll(sprintRaceResultEntities)

        val result = dao.getSprintRaceResultsBySeasonAndRound(season = "2023", round = 1).first()

        assertContentEquals(sprintRaceResultsWithDriverAndConstructor, result)
    }
}
