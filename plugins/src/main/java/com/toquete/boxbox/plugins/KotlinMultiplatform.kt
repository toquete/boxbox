package com.toquete.boxbox.plugins

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
    kotlinExtension: KotlinMultiplatformExtension,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    kotlinExtension.apply {
        // Android target — configured via the KMP Android library DSL (AGP 9+)
        targets.withType(KotlinMultiplatformAndroidLibraryTarget::class.java).configureEach {
            compileSdk = 36
            minSdk = 24

            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
                freeCompilerArgs.addAll(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=kotlinx.coroutines.FlowPreview",
                    "-opt-in=kotlin.Experimental",
                )
            }

            // Enable host (unit) tests with Android resources support
            withHostTestBuilder {}.configure {
                isIncludeAndroidResources = true
            }
        }

        iosArm64()
        iosSimulatorArm64()
        iosX64()

        // Default hierarchy template (Kotlin 2.x) creates iosMain automatically
        // when all three iOS targets are declared.

        sourceSets.commonMain.dependencies {
            implementation(libs.findLibrary("kotlinx.datetime").get())
            implementation(libs.findLibrary("coroutines.core").get())
        }
    }

    // desugar_jdk_libs: coreLibraryDesugaring is not applicable to the new KMP android plugin.
    // Core library desugaring is handled automatically by the KMP Android library plugin.
}
