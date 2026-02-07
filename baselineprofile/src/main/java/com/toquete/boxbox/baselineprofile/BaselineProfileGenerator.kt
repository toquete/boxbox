package com.toquete.boxbox.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Generates a baseline profile for the BoxBox app by exercising critical user journeys.
 *
 * Run this with:
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
            packageName = PACKAGE_NAME,
            includeInStartupProfile = true
        ) {
            // App startup
            pressHome()
            startActivityAndWait()

            // Wait for initial content to load (Standings tab is the default)
            device.waitForIdle()

            // Scroll through driver standings
            val standingsList = device.findObject(By.res("Driver Standings List"))
            standingsList?.run {
                fling(Direction.DOWN)
                device.waitForIdle()
                fling(Direction.UP)
                device.waitForIdle()
            }

            // Navigate to Races tab
            val racesTab = device.findObject(By.desc("Races"))
                ?: device.findObject(By.res("Home Navigation Bar Item RACES"))
            racesTab?.click()
            device.wait(Until.hasObject(By.res("Circuit Name")), TIMEOUT)
            device.waitForIdle()

            // Navigate back to Standings tab
            val standingsTab = device.findObject(By.desc("Standings"))
                ?: device.findObject(By.res("Home Navigation Bar Item STANDINGS"))
            standingsTab?.click()
            device.wait(Until.hasObject(By.res("Driver Standings List")), TIMEOUT)
            device.waitForIdle()

            // Open settings
            val settingsButton = device.findObject(By.res("Home Settings Button"))
            settingsButton?.click()
            device.waitForIdle()

            // Dismiss settings
            device.pressBack()
            device.waitForIdle()
        }
    }

    companion object {
        private const val PACKAGE_NAME = "com.toquete.boxbox"
        private const val TIMEOUT = 5_000L
    }
}
