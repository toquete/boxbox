package com.toquete.boxbox.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Generates a baseline profile for the BoxBox app by exercising critical user journeys.
 *
 * Run this with:
 * ```
 * ./gradlew --no-daemon :baselineprofile:pixel6Api33ProdReleaseBenchmarkAndroidTest
 * ```
 *
 * Or to generate and copy profiles directly into the app:
 * ```
 * ./gradlew --no-daemon :app:generateProdReleaseBaselineProfile
 * ```
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generateProfile() {
        rule.collect(
            packageName = "com.toquete.boxbox",
            includeInStartupProfile = true
        ) {
            // App startup journey
            pressHome()
            startActivityAndWait()

            // Wait for content to load
            device.waitForIdle()
        }
    }
}
