package com.toquete.boxbox.plugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("compose.compiler").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("compose.bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            add("implementation", libs.findBundle("lifecycle").get())
            add("implementation", libs.findBundle("accompanist").get())
            add("implementation", libs.findBundle("compose").get())
            add("implementation", libs.findLibrary("compose.navigation").get())
            add("implementation", libs.findLibrary("hilt.navigation").get())
            add("implementation", libs.findLibrary("compose.material3").get())
            add("implementation", libs.findLibrary("compose.preview").get())
            add("implementation", libs.findLibrary("compose.material.icons").get())
            add("implementation", libs.findLibrary("compose.material.icons.extended").get())
            add("debugImplementation", libs.findLibrary("compose.ui.tooling").get())
        }
    }
}
