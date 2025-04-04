package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.testing.data.circuitEntities
import com.toquete.boxbox.core.testing.data.circuitImageEntities
import com.toquete.boxbox.core.testing.data.countryEntities
import com.toquete.boxbox.core.testing.data.raceEntities
import com.toquete.boxbox.core.testing.data.racesWithCircuits
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertContentEquals

@RunWith(AndroidJUnit4::class)
class RaceDaoTest {

    private lateinit var dao: RaceDao
    private lateinit var circuitDao: CircuitDao
    private lateinit var countryDao: CountryDao
    private lateinit var circuitImageDao: CircuitImageDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.raceDao()
        circuitDao = db.circuitDao()
        countryDao = db.countryDao()
        circuitImageDao = db.circuitImageDao()
    }

    @After
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
