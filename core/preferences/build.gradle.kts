import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kover)
}

kotlin {
    androidLibrary {
        namespace = "com.toquete.boxbox.core.preferences"
        compileSdk = 35
        minSdk = 24

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }
    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach {
        it.binaries.framework {
            baseName = "preferenceKit"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":domain"))
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.coroutines.core)

                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)

                implementation(libs.datastore.preferences)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.coroutines.test)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.koin.android)
            }
        }
        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.test.core)
                implementation(libs.junit.ext)
                implementation(libs.junit)
                implementation(libs.test.runner)
            }
        }
    }
}
