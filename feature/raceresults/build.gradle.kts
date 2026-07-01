plugins {
    id("boxbox.android.library")
    id("boxbox.android.library.compose")
    id("boxbox.android.feature")
}

android {
    namespace = "com.toquete.boxbox.feature.raceresults"
    resourcePrefix = "race_results_"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":domain"))
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    testImplementation(project(":core:testing"))
    testImplementation(libs.robolectric)
    testImplementation(libs.compose.navigation.test)
}
