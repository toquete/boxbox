import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.baselineprofile) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.sonarqube)
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

val projectVersion by extra("1.4.1")

val reportMerge by tasks.registering(ReportMergeTask::class) {
    output.set(rootProject.layout.buildDirectory.file("reports/detekt-results.xml"))
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        config = files("${rootProject.rootDir}/detekt/config.yml")
        buildUponDefaultConfig = true
        autoCorrect = true
        ignoredBuildTypes = listOf("release", "minified")
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            sarif.required.set(false)
            md.required.set(false)

            xml.required.set(true)
            xml.outputLocation.set(file("build/reports/detekt.xml"))

            html.required.set(true)
            html.outputLocation.set(file("build/reports/detekt.html"))

            txt.required.set(true)
            txt.outputLocation.set(file("build/reports/detekt.txt"))
        }
        finalizedBy(reportMerge)
    }

    reportMerge {
        input.from(tasks.withType<Detekt>().map { it.xmlReportFile })
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.8")
    }
}

sonar {
    properties {
        property("sonar.projectKey", "toquete_boxbox")
        property("sonar.organization", "toquete")
        property("sonar.projectName", "BoxBox")
        property("sonar.projectVersion", projectVersion)
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.verbose", "true")
    }
}
