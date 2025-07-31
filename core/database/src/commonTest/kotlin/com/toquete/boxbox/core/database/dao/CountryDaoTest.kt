package com.toquete.boxbox.core.database.dao

import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.RoomDatabaseTest
import com.toquete.boxbox.core.database.mock.countryEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CountryDaoTest : RoomDatabaseTest() {

    private lateinit var dao: CountryDao
    private lateinit var db: BoxBoxDatabase

    @BeforeTest
    fun setUp() {
        db = getInMemoryDatabaseBuilder().build()
        dao = db.countryDao()
    }

    @AfterTest
    fun tearDown() {
        db.close()
    }

    @Test
    fun testCountryInsert() = runTest {
        dao.upsertAll(countryEntities)

        val result = dao.getCountryById(id = "NED").first()

        assertEquals(countryEntities.first(), result)
    }
}
