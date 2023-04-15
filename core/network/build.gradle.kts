plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    id("boxbox.android.remote")
}

android {
    namespace = "com.toquete.boxbox.network"
}

dependencies {
    implementation(project(":core:model"))
}