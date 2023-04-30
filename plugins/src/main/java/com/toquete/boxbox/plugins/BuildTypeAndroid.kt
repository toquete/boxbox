package com.toquete.boxbox.plugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureBuildType(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        buildTypes {
            create("minified") {
                initWith(getByName("debug"))
                isMinifyEnabled = true
            }
        }
    }
}