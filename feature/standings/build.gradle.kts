plugins {
    id("boxbox.android.library")
    id("boxbox.android.library.compose")
    id("boxbox.android.feature")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.feature.standings"
    resourcePrefix = "standings_"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":domain"))

    testImplementation(project(":core:testing"))
    testImplementation(libs.robolectric)
}
