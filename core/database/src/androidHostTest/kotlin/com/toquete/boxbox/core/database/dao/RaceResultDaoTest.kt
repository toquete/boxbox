package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.database.BoxBoxDatabase
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
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertContentEquals

@RunWith(AndroidJUnit4::class)
class RaceResultDaoTest {

    private lateinit var dao: RaceResultDao
    private lateinit var driverDao: DriverDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var raceDao: RaceDao
    private lateinit var circuitDao: CircuitDao
    private lateinit var driverImageDao: DriverImageDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.raceResultDao()
        driverDao = db.driverDao()
        constructorDao = db.constructorDao()
        raceDao = db.raceDao()
        circuitDao = db.circuitDao()
        driverImageDao = db.driverImageDao()
    }

    @After
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
