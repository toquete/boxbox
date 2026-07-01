plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.sync"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":domain"))
    implementation(libs.work.runtime)
    implementation(libs.koin.android)
    implementation(libs.koin.workmanager)
    implementation(libs.hilt.work)
    ksp(libs.hilt.work.compiler)
    implementation(libs.timber)
    implementation(libs.kotlinx.datetime)

    testImplementation(project(":core:testing"))
    testImplementation(libs.robolectric)
    testImplementation(libs.work.testing)
}
