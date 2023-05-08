package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.database.dao.CountryDao
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.dao.FullDriverStandingDao
import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.core.testing.data.constructorImageEntities
import com.toquete.boxbox.core.testing.data.countryEntities
import com.toquete.boxbox.core.testing.data.driverEntities
import com.toquete.boxbox.core.testing.data.driverImageEntities
import com.toquete.boxbox.core.testing.data.driverStandingEntities
import com.toquete.boxbox.core.testing.data.fullDriverStandingEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class FullDriverStandingDaoTest {

    private lateinit var dao: FullDriverStandingDao
    private lateinit var driverDao: DriverDao
    private lateinit var driverStandingDao: DriverStandingDao
    private lateinit var driverImageDao: DriverImageDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var constructorImageDao: ConstructorImageDao
    private lateinit var countryDao: CountryDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.fullDriverStandingDao()
        driverDao = db.driverDao()
        driverStandingDao = db.driverStandingDao()
        driverImageDao = db.driverImageDao()
        constructorDao = db.constructorDao()
        constructorImageDao = db.constructorImageDao()
        countryDao = db.countryDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testFullDriverStandingQuery() = runTest {
        driverDao.insertAll(driverEntities)
        driverStandingDao.insertAll(driverStandingEntities)
        driverImageDao.insertAll(driverImageEntities)
        constructorDao.insertAll(constructorEntities)
        constructorImageDao.insertAll(constructorImageEntities)
        countryDao.insertAll(countryEntities)

        val result = dao.getFullDriverStandings().first()

        assertContentEquals(fullDriverStandingEntities, result)
    }
}
