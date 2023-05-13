plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.core.preferences"
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.datastore.preferences)

    testImplementation(project(":core:testing"))
}