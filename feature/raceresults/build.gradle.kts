plugins {
    id("boxbox.android.library")
    id("boxbox.android.library.compose")
    id("boxbox.android.feature")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.feature.raceresults"
    resourcePrefix = "race_results_"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":domain"))
    debugImplementation(libs.compose.ui.manifest)

    testImplementation(project(":core:testing"))
    testImplementation(libs.robolectric)
    testImplementation(libs.junit.compose)
}
