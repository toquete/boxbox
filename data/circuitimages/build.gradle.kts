import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kover)
}

kotlin {
    androidLibrary {
        namespace = "com.toquete.boxbox.data.circuitimages"
        compileSdk = 35
        minSdk = 24

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }
    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach {
        it.binaries.framework {
            baseName = "circuitImagesKit"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:database"))
                implementation(project(":core:network"))
                implementation(project(":domain"))
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.coroutines.core)

                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)
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
    }
}
