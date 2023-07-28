package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.testing.data.raceEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class RaceDaoTest {

    private lateinit var dao: RaceDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.raceDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testRaceInsert() = runTest {
        dao.upsertAll(raceEntities)

        val result = dao.getRacesBySeason(season = "2023").first()

        assertEquals(raceEntities, result)
    }
}
