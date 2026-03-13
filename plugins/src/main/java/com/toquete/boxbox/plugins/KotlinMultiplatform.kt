package com.toquete.boxbox.plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
    kotlinExtension: KotlinMultiplatformExtension,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    kotlinExtension.apply {
        androidTarget {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
                freeCompilerArgs.addAll(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=kotlinx.coroutines.FlowPreview",
                    "-opt-in=kotlin.Experimental",
                )
            }
        }

        iosArm64()
        iosSimulatorArm64()
        iosX64()

        // Default hierarchy template (Kotlin 2.x) creates iosMain automatically
        // when all three iOS targets are declared — no explicit applyDefaultHierarchyTemplate() needed.

        sourceSets.commonMain.dependencies {
            implementation(libs.findLibrary("kotlinx.datetime").get())
            implementation(libs.findLibrary("coroutines.core").get())
        }
    }

    // Configure Android Gradle Plugin extension (flavors, compile SDK, desugar, etc.)
    extensions.configure<LibraryExtension> {
        compileSdk = 36
        defaultConfig.minSdk = 24
        defaultConfig.targetSdk = 36
        compileOptions {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
        packaging {
            resources {
                excludes += listOf(
                    "/META-INF/{AL2.0,LGPL2.1}",
                    "META-INF/LICENSE.md",
                    "META-INF/LICENSE-notice.md",
                )
            }
        }
        configureFlavors(this)
        configureGradleManagedDevices(this)
    }

    // desugar_jdk_libs must be added via the project-level dependencies block,
    // not inside a KMP source set, because it uses the coreLibraryDesugaring configuration.
    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("desugar.jdk.libs").get())
    }
}
