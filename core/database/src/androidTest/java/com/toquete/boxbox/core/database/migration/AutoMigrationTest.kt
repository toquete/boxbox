package com.toquete.boxbox.core.database.migration

import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.toquete.boxbox.core.database.BoxBoxDatabase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AutoMigrationTest {

    private val testDb = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        BoxBoxDatabase::class.java
    )

    @Test
    @Throws(IOException::class)
    fun migrateFrom1To2() {
        helper.createDatabase(testDb, 1).apply {
            close()
        }

        helper.runMigrationsAndValidate(testDb, 2, true)
    }

    @Test
    @Throws(IOException::class)
    fun migrateFrom2To3() {
        helper.createDatabase(testDb, 2).apply {
            close()
        }

        helper.runMigrationsAndValidate(testDb, 3, true)
    }
}
