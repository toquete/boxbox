package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.testing.data.circuitEntities
import com.toquete.boxbox.core.testing.data.countryEntities
import com.toquete.boxbox.core.testing.data.raceEntities
import com.toquete.boxbox.core.testing.data.racesWithCircuits
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class RaceDaoTest {

    private lateinit var dao: RaceDao
    private lateinit var circuitDao: CircuitDao
    private lateinit var countryDao: CountryDao
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
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testRaceInsert() = runTest {
        countryDao.upsertAll(countryEntities)
        circuitDao.upsertAll(circuitEntities)
        dao.upsertAll(raceEntities)

        val result = dao.getRacesBySeason(season = "2023").first()

        assertContentEquals(racesWithCircuits, result)
    }
}
