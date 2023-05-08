package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.dao.FullConstructorStandingDao
import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.core.testing.data.constructorImageEntities
import com.toquete.boxbox.core.testing.data.constructorStandingEntities
import com.toquete.boxbox.core.testing.data.fullConstructorStandingEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class FullConstructorStandingDaoTest {

    private lateinit var dao: FullConstructorStandingDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var constructorStandingDao: ConstructorStandingDao
    private lateinit var constructorImageDao: ConstructorImageDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.fullConstructorStandingDao()
        constructorDao = db.constructorDao()
        constructorStandingDao = db.constructorStandingDao()
        constructorImageDao = db.constructorImageDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testFullConstructorStandingQuery() = runTest {
        constructorDao.insertAll(constructorEntities)
        constructorStandingDao.insertAll(constructorStandingEntities)
        constructorImageDao.insertAll(constructorImageEntities)

        val result = dao.getFullConstructorStandings().first()

        assertContentEquals(fullConstructorStandingEntities, result)
    }
}
