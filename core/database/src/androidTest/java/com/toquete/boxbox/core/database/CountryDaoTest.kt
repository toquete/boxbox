package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.testing.data.countryEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CountryDaoTest {

    private lateinit var dao: CountryDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.countryDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testCountryInsert() = runTest {
        dao.insertAll(countryEntities)

        val result = dao.getCountryById(id = "NED").first()

        assertEquals(countryEntities.first(), result)
    }
}
