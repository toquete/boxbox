plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    id("boxbox.android.koin")
}

android {
    namespace = "com.toquete.boxbox.core.preferences"
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.datastore.preferences)

    testImplementation(project(":core:testing"))
}
