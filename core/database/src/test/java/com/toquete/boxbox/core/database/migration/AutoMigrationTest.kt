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

    @Test
    @Throws(IOException::class)
    fun migrateFrom3To4() {
        helper.createDatabase(testDb, 3).apply {
            close()
        }

        helper.runMigrationsAndValidate(testDb, 4, true)
    }

    @Test
    @Throws(IOException::class)
    fun migrateFrom4To5() {
        helper.createDatabase(testDb, 4).apply {
            close()
        }

        helper.runMigrationsAndValidate(testDb, 5, true)
    }

    @Test
    @Throws(IOException::class)
    fun migrateFrom7To8() {
        helper.createDatabase(testDb, 7).apply {
            close()
        }

        helper.runMigrationsAndValidate(testDb, 8, true)
    }

    @Test
    @Throws(IOException::class)
    fun migrateFrom9To10() {
        helper.createDatabase(testDb, 9).apply {
            close()
        }

        helper.runMigrationsAndValidate(testDb, 10, true)
    }

    @Test
    @Throws(IOException::class)
    fun migrateFrom10To11() {
        helper.createDatabase(testDb, 10).apply {
            close()
        }

        helper.runMigrationsAndValidate(testDb, 11, true)
    }
}
