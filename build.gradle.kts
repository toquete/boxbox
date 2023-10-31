import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    alias(libs.plugins.detekt)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        config = files("${rootProject.rootDir}/detekt/config.yml")
        buildUponDefaultConfig = true
        autoCorrect = true
        ignoredBuildTypes = listOf("release", "minified")
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            xml.required.set(false)
            sarif.required.set(false)
            md.required.set(false)

            html.required.set(true)
            html.outputLocation.set(file("build/reports/detekt.html"))

            txt.required.set(true)
            txt.outputLocation.set(file("build/reports/detekt.txt"))
        }
    }

    tasks.withType<KaptGenerateStubsTask>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.2")
    }
}
