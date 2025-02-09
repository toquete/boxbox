package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.core.testing.data.driverEntities
import com.toquete.boxbox.core.testing.data.sprintRaceResultEntities
import com.toquete.boxbox.core.testing.data.sprintRaceResultsWithDriverAndConstructor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertContentEquals

@RunWith(AndroidJUnit4::class)
class SprintRaceResultDaoTest {

    private lateinit var dao: SprintRaceResultDao
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
        dao = db.sprintRaceResultDao()
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
    fun testSprintRaceResultInsert() = runTest {
        driverDao.upsertAll(driverEntities)
        constructorDao.upsertAll(constructorEntities)
        dao.upsertAll(sprintRaceResultEntities)

        val result = dao.getSprintRaceResultsBySeasonAndRound(season = "2023", round = 1).first()

        assertContentEquals(sprintRaceResultsWithDriverAndConstructor, result)
    }
}
