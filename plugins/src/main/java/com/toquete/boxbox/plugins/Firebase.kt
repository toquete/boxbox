package com.toquete.boxbox.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import org.gradle.kotlin.dsl.configure

internal fun configureFirebase(
    applicationExtension: ApplicationExtension
) {
    applicationExtension.apply {
        productFlavors.configureEach {
            if (this.name == BoxBoxFlavor.DEMO.name.lowercase()) {
                configure<CrashlyticsExtension> {
                    mappingFileUploadEnabled = false
                }
            }
        }
    }
}
