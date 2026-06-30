plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.core.common"
}

dependencies {
    implementation(libs.koin.android)
}