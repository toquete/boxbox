plugins {
    id("boxbox.android.library")
    id("boxbox.android.library.compose")
    id("boxbox.android.feature")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.feature.driverstandings"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":domain:fulldriverstandings"))
}