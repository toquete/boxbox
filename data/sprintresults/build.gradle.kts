plugins {
    id("boxbox.android.library")
    id("boxbox.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.toquete.boxbox.data.sprintresults"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    demoImplementation(libs.serialization)
    testImplementation(project(":core:testing"))
}
