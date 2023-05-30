plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    id("boxbox.android.remote")
}

android {
    namespace = "com.toquete.boxbox.core.network"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.mockwebserver)

    testImplementation(project(":core:testing"))
}
