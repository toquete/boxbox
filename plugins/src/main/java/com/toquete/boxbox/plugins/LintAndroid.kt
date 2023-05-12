package com.toquete.boxbox.plugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureLintAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        lint {
            textReport = false
            xmlReport = false
            sarifReport = false
            htmlReport = true
            htmlOutput = file("$buildDir/reports/${project.path}-lint-report.html")
        }
    }
}
