@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("boxbox.android.application")
    id("boxbox.android.application.compose")
    id("boxbox.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.toquete.boxbox"

    defaultConfig {
        applicationId = "com.toquete.boxbox"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:driverstandings"))
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
}