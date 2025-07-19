plugins {
    id("boxbox.android.library")
    id("boxbox.android.library.compose")
    id("boxbox.android.feature")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.feature.home"
    resourcePrefix = "home_"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core:ui"))
    implementation(libs.play.services.ads)
    debugImplementation(libs.compose.ui.manifest)

    testImplementation(project(":core:testing"))
    testImplementation(libs.robolectric)
    testImplementation(libs.junit.compose)
}
