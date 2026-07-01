plugins {
    id("boxbox.android.library")
}

android {
    namespace = "com.toquete.boxbox.core.common"
}

dependencies {
    implementation(libs.koin.android)
}