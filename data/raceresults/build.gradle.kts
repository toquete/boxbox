plugins {
    id("boxbox.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.toquete.boxbox.data.raceresults"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":domain"))
    implementation(libs.koin.android)
    demoImplementation(libs.serialization)
    testImplementation(project(":core:testing"))
}
