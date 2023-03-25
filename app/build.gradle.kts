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
    implementation(libs.core.ktx)
    implementation(libs.bundles.compose)
    implementation(libs.compose.material)
    implementation(libs.compose.preview)
    implementation(libs.bundles.lifecycle)

    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.coil)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.junit.compose)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.manifest)
}