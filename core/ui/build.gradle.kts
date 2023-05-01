plugins {
    id("boxbox.android.library")
    id("boxbox.android.library.compose")
}

android {
    namespace = "com.toquete.boxbox.core.ui"
}

dependencies {
    implementation(libs.bundles.coil)
}