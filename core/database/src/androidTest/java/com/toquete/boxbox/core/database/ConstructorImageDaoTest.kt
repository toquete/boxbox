package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.testing.data.constructorImageEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ConstructorImageDaoTest {

    private lateinit var dao: ConstructorImageDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.constructorImageDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testConstructorImageInsert() = runTest {
        dao.insertAll(constructorImageEntities)

        val result = dao.getConstructorImageById(id = "red_bull").first()

        assertEquals(constructorImageEntities.first(), result)
    }
}
