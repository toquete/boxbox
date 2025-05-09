plugins {
    id("boxbox.android.library")
    id("boxbox.android.koin")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.toquete.boxbox.data.races"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":domain"))
    demoImplementation(libs.serialization)
    testImplementation(project(":core:testing"))
}
