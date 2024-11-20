plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.toquete.boxbox.data.raceresults"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":domain"))
    demoImplementation(libs.serialization)
    testImplementation(project(":core:testing"))
}
