import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidLibrary {
        namespace = "com.toquete.boxbox.core.testing"
        compileSdk = 35
        minSdk = 24

        withHostTest {}

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }
    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach {
        it.binaries.framework {
            baseName = "testingKit"
        }
    }

    sourceSets {
        androidMain {
            dependencies {
                implementation(project(":core:model"))
                implementation(project(":core:database"))
                implementation(project(":core:network"))
                api(libs.junit)
                api(libs.junit.ext)
                api(kotlin("test"))
                api(libs.coroutines.test)
                api(libs.mockk)
                api(libs.test.core)
                api(libs.test.runner)
                api(libs.test.rules)
                api(libs.espresso)
            }
        }
    }
}
