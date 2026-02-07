plugins {
    id("boxbox.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.toquete.boxbox.core.navigation"
}

dependencies {
    implementation(libs.serialization)
}
