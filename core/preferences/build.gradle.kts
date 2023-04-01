plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
}

android {
    namespace = "com.toquete.boxbox.preferences"
}

dependencies {
    implementation(libs.datastore.preferences)
}