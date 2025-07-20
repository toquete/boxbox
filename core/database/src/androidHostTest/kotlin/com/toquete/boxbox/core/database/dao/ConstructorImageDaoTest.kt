package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.mock.constructorImageEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
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
        dao.upsertAll(constructorImageEntities)

        val result = dao.getConstructorImageById(id = "red_bull").first()

        assertEquals(constructorImageEntities.first(), result)
    }
}
