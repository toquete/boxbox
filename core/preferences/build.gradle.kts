plugins {
    id("boxbox.android.library")
}

android {
    namespace = "com.toquete.boxbox.core.preferences"
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.datastore.preferences)

    testImplementation(project(":core:testing"))

    implementation(libs.koin.android)
}
