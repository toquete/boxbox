package com.toquete.boxbox.plugins

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project

/**
 * Disable unnecessary Android instrumented tests for the [project] if there is no `androidTest` folder.
 * Otherwise, these projects would be compiled, packaged, installed and ran only to end-up with the following message:
 *
 * > Starting 0 tests on AVD
 *
 * Note: this could be improved by checking other potential sourceSets based on buildTypes and flavors.
 */
internal fun LibraryAndroidComponentsExtension.disableUnnecessaryAndroidTests(
    project: Project,
) = beforeVariants {
    it.enableAndroidTest = it.enableAndroidTest && project.projectDir.resolve("src/androidTest").exists()
}

internal fun ApplicationAndroidComponentsExtension.disableUnnecessaryAndroidTests(
    project: Project,
) = beforeVariants {
    it.enableAndroidTest = it.enableAndroidTest && project.projectDir.resolve("src/androidTest").exists()
}
