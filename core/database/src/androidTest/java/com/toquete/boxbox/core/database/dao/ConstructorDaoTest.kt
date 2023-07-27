package com.toquete.boxbox.core.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.testing.data.constructorEntities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class ConstructorDaoTest {

    private lateinit var dao: ConstructorDao
    private lateinit var db: BoxBoxDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).build()
        dao = db.constructorDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testConstructorsInsert() = runTest {
        dao.upsertAll(constructorEntities)

        val result = dao.getConstructors().first()

        assertContentEquals(constructorEntities, result)
    }
}
