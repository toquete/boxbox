package com.toquete.boxbox.core.database.migration

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.MIGRATION_5_6
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    private val testDb = "migration-test"
    private val allMigrations = arrayOf(MIGRATION_5_6)

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        BoxBoxDatabase::class.java
    )

    @Test
    @Throws(IOException::class)
    fun migrateAll() {
        // Create earliest version of the database.
        helper.createDatabase(testDb, 1).apply {
            close()
        }

        // Open latest version of the database. Room validates the schema
        // once all migrations execute.
        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            BoxBoxDatabase::class.java,
            testDb
        ).addMigrations(*allMigrations).build().apply {
            openHelper.writableDatabase.close()
        }
    }
}
